package me.digitalby.browserdetect

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import me.digitalby.browserdetect.internal.BrowserCatalog
import me.digitalby.browserdetect.internal.CatalogEntry

public class BrowserDetector internal constructor(
    private val context: Context,
) {
    public fun detect(): List<BrowserInfo> {
        val pm = context.packageManager
        val candidatePackages = collectCandidatePackages(pm)
        val defaultBrowserPackage = resolveDefaultBrowserPackage(pm)

        return candidatePackages
            .mapNotNull { pkg -> buildInfo(pm, pkg, defaultBrowserPackage) }
            .sortedWith(
                compareByDescending<BrowserInfo> { it.isDefault }
                    .thenBy { it.category.ordinal }
                    .thenBy { it.displayLabel.lowercase() },
            )
    }

    public suspend fun detectAsync(): List<BrowserInfo> = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) { detect() }

    private fun collectCandidatePackages(pm: PackageManager): Set<String> {
        val packages = mutableSetOf<String>()

        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROBE_URL))
        val resolved: List<ResolveInfo> =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.queryIntentActivities(viewIntent, PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong()))
            } else {
                @Suppress("DEPRECATION")
                pm.queryIntentActivities(viewIntent, PackageManager.MATCH_ALL)
            }
        resolved.forEach { ri -> packages.add(ri.activityInfo.packageName) }

        BrowserCatalog.entries.forEach { entry ->
            if (isPackageInstalled(pm, entry.primaryPackage)) {
                packages.add(entry.primaryPackage)
            }
            entry.alternatePackages.forEach { alt ->
                if (isPackageInstalled(pm, alt)) {
                    packages.add(alt)
                }
            }
        }

        return packages
    }

    private fun isPackageInstalled(
        pm: PackageManager,
        packageName: String,
    ): Boolean =
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L))
            } else {
                @Suppress("DEPRECATION")
                pm.getPackageInfo(packageName, 0)
            }
            true
        } catch (_: PackageManager.NameNotFoundException) {
            false
        }

    private fun resolveDefaultBrowserPackage(pm: PackageManager): String? {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PROBE_URL))
        val resolve: ResolveInfo? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.resolveActivity(intent, PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong()))
            } else {
                @Suppress("DEPRECATION")
                pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            }
        val pkg = resolve?.activityInfo?.packageName ?: return null
        // System disambiguator activity (no default chosen) is not a real browser.
        if (pkg.equals("android", ignoreCase = true)) return null
        return pkg
    }

    private fun buildInfo(
        pm: PackageManager,
        packageName: String,
        defaultBrowserPackage: String?,
    ): BrowserInfo? {
        val packageInfo: PackageInfo =
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L))
                } else {
                    @Suppress("DEPRECATION")
                    pm.getPackageInfo(packageName, 0)
                }
            } catch (_: PackageManager.NameNotFoundException) {
                return null
            }

        val applicationInfo = packageInfo.applicationInfo ?: return null
        val label = pm.getApplicationLabel(applicationInfo).toString()
        val entry: CatalogEntry? = BrowserCatalog.byPackage[packageName]
        val versionCode =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode.toLong()
            }

        return BrowserInfo(
            id = entry?.id ?: BrowserId.Unknown(packageName),
            packageName = packageName,
            displayLabel = label,
            versionName = packageInfo.versionName,
            versionCode = versionCode,
            isDefault = packageName == defaultBrowserPackage,
            category = entry?.category ?: BrowserCategory.Unknown,
            regionHints = entry?.regionHints ?: emptyList(),
            homepageUrl = entry?.homepageUrl,
        )
    }

    public companion object {
        private const val PROBE_URL = "https://example.com/"

        public fun create(context: Context): BrowserDetector = BrowserDetector(context.applicationContext)
    }
}
