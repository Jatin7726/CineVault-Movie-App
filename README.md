# CineVault

An offline-first movie app built to cover most of what comes up in an Android
interview in one real project, instead of many disconnected demos.

## Stack

- **UI:** Jetpack Compose, Material 3, Navigation Compose
- **DI:** Hilt
- **Architecture:** Clean Architecture (presentation → domain → data), MVVM
- **Async:** Kotlin Coroutines + Flow (StateFlow, debounce, flatMapLatest)
- **Networking:** Retrofit + OkHttp + kotlinx.serialization
- **Local storage:** Room (offline cache), single source of truth
- **Pagination:** Paging 3
- **Images:** Coil
- **Testing:** JUnit, Turbine, kotlinx-coroutines-test, MockWebServer (scaffolded)

