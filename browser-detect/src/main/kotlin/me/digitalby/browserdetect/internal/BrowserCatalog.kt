package me.digitalby.browserdetect.internal

import me.digitalby.browserdetect.BrowserCategory
import me.digitalby.browserdetect.BrowserId

internal data class CatalogEntry(
    val id: BrowserId,
    val primaryPackage: String,
    val alternatePackages: List<String> = emptyList(),
    val displayName: String,
    val category: BrowserCategory,
    val regionHints: List<String> = emptyList(),
    val homepageUrl: String? = null,
)

internal object BrowserCatalog {
    val entries: List<CatalogEntry> =
        listOf(
            CatalogEntry(
                id = BrowserId.Chrome,
                primaryPackage = "com.android.chrome",
                displayName = "Chrome",
                category = BrowserCategory.International,
                homepageUrl = "https://www.google.com/chrome/",
            ),
            CatalogEntry(
                id = BrowserId.ChromeBeta,
                primaryPackage = "com.chrome.beta",
                displayName = "Chrome Beta",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.ChromeDev,
                primaryPackage = "com.chrome.dev",
                displayName = "Chrome Dev",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.ChromeCanary,
                primaryPackage = "com.chrome.canary",
                displayName = "Chrome Canary",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.Firefox,
                primaryPackage = "org.mozilla.firefox",
                displayName = "Firefox",
                category = BrowserCategory.International,
                homepageUrl = "https://www.mozilla.org/firefox/",
            ),
            CatalogEntry(
                id = BrowserId.FirefoxBeta,
                primaryPackage = "org.mozilla.firefox_beta",
                displayName = "Firefox Beta",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.FirefoxNightly,
                primaryPackage = "org.mozilla.fenix",
                displayName = "Firefox Nightly",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.FirefoxFocus,
                primaryPackage = "org.mozilla.focus",
                displayName = "Firefox Focus",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://www.mozilla.org/firefox/browsers/mobile/focus/",
            ),
            CatalogEntry(
                id = BrowserId.FennecFDroid,
                primaryPackage = "org.mozilla.fennec_fdroid",
                displayName = "Fennec F-Droid",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://f-droid.org/packages/org.mozilla.fennec_fdroid/",
            ),
            CatalogEntry(
                id = BrowserId.Edge,
                primaryPackage = "com.microsoft.emmx",
                displayName = "Microsoft Edge",
                category = BrowserCategory.International,
                homepageUrl = "https://www.microsoft.com/edge",
            ),
            CatalogEntry(
                id = BrowserId.Brave,
                primaryPackage = "com.brave.browser",
                displayName = "Brave",
                category = BrowserCategory.International,
                homepageUrl = "https://brave.com/",
            ),
            CatalogEntry(
                id = BrowserId.Opera,
                primaryPackage = "com.opera.browser",
                displayName = "Opera",
                category = BrowserCategory.International,
                homepageUrl = "https://www.opera.com/",
            ),
            CatalogEntry(
                id = BrowserId.OperaGx,
                primaryPackage = "com.opera.gx",
                displayName = "Opera GX",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.OperaMini,
                primaryPackage = "com.opera.mini.native",
                displayName = "Opera Mini",
                category = BrowserCategory.Regional,
                regionHints = listOf("AF", "IN", "ID", "BR"),
                homepageUrl = "https://www.opera.com/mobile/mini",
            ),
            CatalogEntry(
                id = BrowserId.SamsungInternet,
                primaryPackage = "com.sec.android.app.sbrowser",
                displayName = "Samsung Internet",
                category = BrowserCategory.International,
            ),
            CatalogEntry(
                id = BrowserId.DuckDuckGo,
                primaryPackage = "com.duckduckgo.mobile.android",
                displayName = "DuckDuckGo Privacy Browser",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://duckduckgo.com/app",
            ),
            CatalogEntry(
                id = BrowserId.Vivaldi,
                primaryPackage = "com.vivaldi.browser",
                displayName = "Vivaldi",
                category = BrowserCategory.International,
                homepageUrl = "https://vivaldi.com/",
            ),
            CatalogEntry(
                id = BrowserId.Tor,
                primaryPackage = "org.torproject.torbrowser",
                displayName = "Tor Browser",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://www.torproject.org/",
            ),
            CatalogEntry(
                id = BrowserId.Kiwi,
                primaryPackage = "com.kiwibrowser.browser",
                displayName = "Kiwi Browser",
                category = BrowserCategory.International,
                homepageUrl = "https://kiwibrowser.com/",
            ),
            CatalogEntry(
                id = BrowserId.Ecosia,
                primaryPackage = "com.ecosia.android",
                displayName = "Ecosia",
                category = BrowserCategory.International,
                homepageUrl = "https://www.ecosia.org/",
            ),
            CatalogEntry(
                id = BrowserId.Mull,
                primaryPackage = "us.spotco.fennec_dos",
                displayName = "Mull",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.Mulch,
                primaryPackage = "us.spotco.mulch",
                displayName = "Mulch",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.Bromite,
                primaryPackage = "org.bromite.bromite",
                displayName = "Bromite",
                category = BrowserCategory.Legacy,
                homepageUrl = "https://www.bromite.org/",
            ),
            CatalogEntry(
                id = BrowserId.Cromite,
                primaryPackage = "org.cromite.cromite",
                displayName = "Cromite",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://github.com/uazo/cromite",
            ),
            CatalogEntry(
                id = BrowserId.Iceraven,
                primaryPackage = "io.github.forkmaintainers.iceraven",
                displayName = "Iceraven",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.Ironfox,
                primaryPackage = "org.ironfoxoss.ironfox",
                displayName = "IronFox",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.Lightning,
                primaryPackage = "acr.browser.lightning",
                displayName = "Lightning",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.PrivacyBrowser,
                primaryPackage = "com.stoutner.privacybrowser.standard",
                displayName = "Privacy Browser",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://www.stoutner.com/privacy-browser/",
            ),
            CatalogEntry(
                id = BrowserId.Ghostery,
                primaryPackage = "com.ghostery.android.ghostery",
                displayName = "Ghostery Privacy Browser",
                category = BrowserCategory.Privacy,
            ),
            CatalogEntry(
                id = BrowserId.Yandex,
                primaryPackage = "com.yandex.browser",
                displayName = "Yandex Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("RU", "BY", "KZ"),
                homepageUrl = "https://browser.yandex.com/",
            ),
            CatalogEntry(
                id = BrowserId.YandexLite,
                primaryPackage = "com.yandex.browser.lite",
                displayName = "Yandex Browser Lite",
                category = BrowserCategory.Regional,
                regionHints = listOf("RU", "BY", "KZ"),
            ),
            CatalogEntry(
                id = BrowserId.Uc,
                primaryPackage = "com.UCMobile.intl",
                displayName = "UC Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CN", "IN", "ID"),
            ),
            CatalogEntry(
                id = BrowserId.UcMini,
                primaryPackage = "com.uc.browser.en",
                displayName = "UC Browser Mini",
                category = BrowserCategory.Regional,
                regionHints = listOf("IN", "ID"),
            ),
            CatalogEntry(
                id = BrowserId.Qq,
                primaryPackage = "com.tencent.mtt",
                displayName = "QQ Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CN"),
            ),
            CatalogEntry(
                id = BrowserId.Baidu,
                primaryPackage = "com.baidu.browser.apps",
                displayName = "Baidu Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CN"),
            ),
            CatalogEntry(
                id = BrowserId.Quark,
                primaryPackage = "com.quark.browser",
                displayName = "Quark Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CN"),
            ),
            CatalogEntry(
                id = BrowserId.Via,
                primaryPackage = "mark.via.gp",
                displayName = "Via Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CN"),
            ),
            CatalogEntry(
                id = BrowserId.Orbita,
                primaryPackage = "by.orbita.browser",
                displayName = "Orbita",
                category = BrowserCategory.Regional,
                regionHints = listOf("BY"),
            ),
            CatalogEntry(
                id = BrowserId.Atlas,
                primaryPackage = "cz.seznam.sbrowser",
                displayName = "Seznam Browser",
                category = BrowserCategory.Regional,
                regionHints = listOf("CZ"),
                homepageUrl = "https://prohlizec.seznam.cz/",
            ),
            CatalogEntry(
                id = BrowserId.Aloha,
                primaryPackage = "com.alohamobile.browser",
                displayName = "Aloha Browser",
                category = BrowserCategory.Privacy,
                homepageUrl = "https://alohabrowser.com/",
            ),
            CatalogEntry(
                id = BrowserId.Puffin,
                primaryPackage = "com.cloudmosa.puffinFree",
                displayName = "Puffin Browser",
                category = BrowserCategory.Novelty,
                homepageUrl = "https://www.puffin.com/",
            ),
            CatalogEntry(
                id = BrowserId.Dolphin,
                primaryPackage = "mobi.mgeek.TunnyBrowser",
                displayName = "Dolphin",
                category = BrowserCategory.Legacy,
            ),
            CatalogEntry(
                id = BrowserId.Maxthon,
                primaryPackage = "com.mx.browser",
                displayName = "Maxthon",
                category = BrowserCategory.Legacy,
                homepageUrl = "https://www.maxthon.com/",
            ),
            CatalogEntry(
                id = BrowserId.CmBrowser,
                primaryPackage = "com.ksmobile.cb",
                displayName = "CM Browser",
                category = BrowserCategory.Legacy,
            ),
        )

    val byPackage: Map<String, CatalogEntry> =
        run {
            val all = entries
            buildMap {
                all.forEach { entry ->
                    put(entry.primaryPackage, entry)
                    entry.alternatePackages.forEach { alt -> put(alt, entry) }
                }
            }
        }
}
