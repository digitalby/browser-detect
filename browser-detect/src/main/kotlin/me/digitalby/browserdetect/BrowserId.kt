package me.digitalby.browserdetect

/**
 * Stable identifier for a browser known to the catalog, or [Unknown] for any
 * browser found on the device that the library does not have metadata for.
 *
 * Adding a new known subtype is a breaking change for consumers using
 * exhaustive `when`. That is intentional: callers should be reminded by the
 * compiler when a new known browser ships, instead of silently falling
 * through.
 */
public sealed class BrowserId {
    public data object Chrome : BrowserId()

    public data object ChromeBeta : BrowserId()

    public data object ChromeDev : BrowserId()

    public data object ChromeCanary : BrowserId()

    public data object Firefox : BrowserId()

    public data object FirefoxBeta : BrowserId()

    public data object FirefoxNightly : BrowserId()

    public data object FirefoxFocus : BrowserId()

    public data object FennecFDroid : BrowserId()

    public data object Edge : BrowserId()

    public data object Brave : BrowserId()

    public data object Opera : BrowserId()

    public data object OperaGx : BrowserId()

    public data object OperaMini : BrowserId()

    public data object SamsungInternet : BrowserId()

    public data object DuckDuckGo : BrowserId()

    public data object Vivaldi : BrowserId()

    public data object Tor : BrowserId()

    public data object Kiwi : BrowserId()

    public data object Ecosia : BrowserId()

    public data object Mull : BrowserId()

    public data object Mulch : BrowserId()

    public data object Bromite : BrowserId()

    public data object Cromite : BrowserId()

    public data object Iceraven : BrowserId()

    public data object Ironfox : BrowserId()

    public data object Lightning : BrowserId()

    public data object PrivacyBrowser : BrowserId()

    public data object Ghostery : BrowserId()

    public data object Yandex : BrowserId()

    public data object YandexLite : BrowserId()

    public data object Uc : BrowserId()

    public data object UcMini : BrowserId()

    public data object Qq : BrowserId()

    public data object Baidu : BrowserId()

    public data object Quark : BrowserId()

    public data object Via : BrowserId()

    public data object Orbita : BrowserId()

    public data object Atlas : BrowserId()

    public data object Aloha : BrowserId()

    public data object Puffin : BrowserId()

    public data object Dolphin : BrowserId()

    public data object Maxthon : BrowserId()

    public data object CmBrowser : BrowserId()

    public data class Unknown(
        val packageName: String,
    ) : BrowserId()
}
