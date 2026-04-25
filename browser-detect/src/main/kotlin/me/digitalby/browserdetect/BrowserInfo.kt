package me.digitalby.browserdetect

import android.content.Context
import android.graphics.drawable.Drawable

public data class BrowserInfo(
    val id: BrowserId,
    val packageName: String,
    val displayLabel: String,
    val versionName: String?,
    val versionCode: Long,
    val isDefault: Boolean,
    val category: BrowserCategory,
    val regionHints: List<String>,
    val homepageUrl: String?,
) {
    public fun loadIcon(context: Context): Drawable {
        val pm = context.packageManager
        val appInfo = pm.getApplicationInfo(packageName, 0)
        return pm.getApplicationIcon(appInfo)
    }
}
