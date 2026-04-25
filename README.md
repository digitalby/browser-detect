# browser-detect

[![JitPack](https://jitpack.io/v/digitalby/browser-detect.svg)](https://jitpack.io/#digitalby/browser-detect)
[![CI](https://github.com/digitalby/browser-detect/actions/workflows/ci.yml/badge.svg)](https://github.com/digitalby/browser-detect/actions/workflows/ci.yml)

Lightweight Android library that enumerates installed web browsers on the device. No activities, no UI, no `QUERY_ALL_PACKAGES`. Includes a curated catalog of common, niche, regional, and legacy browsers so callers can branch on a typed `BrowserId` instead of magic package strings.

## Install

Add JitPack to your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency:

```kotlin
dependencies {
    implementation("com.github.digitalby:browser-detect:0.1.0")
}
```

## Usage

```kotlin
val detector = BrowserDetector.create(context)
val browsers: List<BrowserInfo> = detector.detect()

browsers.forEach { info ->
    Log.d("Browsers", "${info.displayLabel} (${info.packageName}) v${info.versionName}")
    when (val id = info.id) {
        BrowserId.Chrome -> println("Found Chrome")
        BrowserId.Firefox -> println("Found Firefox")
        is BrowserId.Unknown -> println("Unknown browser: ${id.packageName}")
        else -> Unit
    }
}

val firstAvailable = browsers.firstOrNull() ?: return
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://digitalby.me"))
    .setPackage(firstAvailable.packageName)
context.startActivity(intent)
```

For coroutine callers, use `detectAsync()` which dispatches to `Dispatchers.IO`.

## Browser catalog

The library ships a known catalog with stable identifiers per browser. Categories: `International`, `Privacy`, `Regional`, `Legacy`, `Novelty`. Browsers present on the device but not in the catalog are returned as `BrowserId.Unknown(packageName)` so they remain usable.

International (Chrome, Firefox, Edge, Brave, Opera, Opera GX, Samsung Internet, DuckDuckGo, Vivaldi, Tor, Kiwi, Ecosia), privacy (Mull, Mulch, Cromite, Iceraven, Fennec F-Droid, Ironfox, Lightning, Privacy Browser, Firefox Focus, Ghostery), regional (Yandex, Yandex Lite, UC Browser, UC Mini, Opera Mini, QQ, Baidu, Quark, Via, Orbita, Atlas, Aloha, Puffin), legacy/novelty (Bromite, Dolphin, Maxthon, CM Browser).

## Stability

`BrowserId` is a sealed class. Adding a new known browser is a breaking change at the API level, so callers using `when` over `BrowserId` will be reminded by the compiler to handle it. This is by design and lets consumers pattern-match exhaustively without `else` branches.

## Contributing a browser

1. Add a new `object` to `BrowserId`.
2. Add a `CatalogEntry` to `BrowserCatalog.entries`.
3. Add a `<package android:name="..." />` to the library `AndroidManifest.xml` `<queries>` block.

CI runs `spotlessCheck`, builds both modules, and runs unit tests.

## License

MIT. See [LICENSE](LICENSE).
