# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Initial library scaffold: `BrowserDetector`, `BrowserInfo`, `BrowserId` sealed class.
- Curated catalog covering ~45 browsers across international, privacy, regional, legacy, and novelty categories.
- Two-tier `<queries>` manifest declaration (intent filter + per-package list) for Android 11+ visibility.
- Sample app (`:sample`) with Compose UI listing detected browsers.
- Spotless + ktlint formatting, GitHub Actions CI, JitPack publishing.
