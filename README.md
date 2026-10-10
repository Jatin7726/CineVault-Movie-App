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

## Setup

1. Get a TMDB Read Access Token from themoviedb.org → Settings → API.
2. Copy `local.properties.example` to `local.properties` and paste your token:
   ```
   TMDB_TOKEN=your_token_here
   ```
3. Open in Android Studio (Koala+), let Gradle sync, run on an emulator or device.

`local.properties` is already in `.gitignore` - the token never gets committed
and is injected into `BuildConfig.TMDB_TOKEN` at build time.

## Project structure (Clean Architecture)

```
presentation/   Compose screens + ViewModels (Android/Compose-aware)
domain/         Models, repository interface, use cases (pure Kotlin, no Android)
data/           Retrofit DTOs, Room entities, repository implementation, mappers
di/             Hilt modules wiring it all together
```

The dependency rule: `presentation` depends on `domain`, `data` depends on
`domain`, but `domain` depends on nothing. The ViewModel never imports
Retrofit or Room types directly - only `MovieRepository`, an interface
defined in `domain`.

## Where each concept lives

| Concept | File |
|---|---|
| Hilt setup | `CineVaultApp.kt`, `di/NetworkModule.kt`, `di/DatabaseModule.kt`, `di/RepositoryModule.kt` |
| `@Binds` vs `@Provides` | `RepositoryModule` (`@Binds`) vs `NetworkModule` (`@Provides`) |
| Offline-first / single source of truth | `MovieRepositoryImpl.getTrendingMovies()` - emits cache first, then refreshes |
| Paging 3 | `data/paging/PopularMoviesPagingSource.kt`, `HomeViewModel.popularMovies` |
| Flow operators (debounce, flatMapLatest) | `presentation/search/SearchViewModel.kt` |
| `StateFlow` vs one-shot events | `HomeUiState` as StateFlow; add a `Channel`-based event flow if you need snackbars |
| Navigation Compose + type-safe args | `presentation/navigation/NavGraph.kt`, `Screen.kt` |
| `SavedStateHandle` | `DetailViewModel` - reads the `movieId` nav arg, survives process death |
| Room + Flow | `data/local/MovieDao.kt` |
| OkHttp interceptor / auth header | `data/remote/AuthInterceptor.kt` |
| Compose state hoisting | `SearchScreen` (state lives in the ViewModel, not the composable) |
| Reusable composable | `presentation/common/MoviePosterCard.kt` |
| Unit testing a ViewModel | `app/src/test/.../HomeViewModelTest.kt` (Turbine + fake repository) |
| Material 3 theming | `presentation/theme/` |

## What's intentionally left as an extension (good interview talking points)

These are a natural "what would you add next?" answer:

- **WorkManager + notifications** for "remind me to watch" on a release date.
- **DataStore** for persisting theme (dark/light) and sort-order preference.
- **RemoteMediator** instead of a network-only `PagingSource`, to make the
  popular-movies grid offline-first too, not just trending.
- **BiometricPrompt** app-lock, shown in the Profile screen mockup.
- **CameraX / photo picker** for a profile photo.
- **Baseline Profiles / LeakCanary / Macrobenchmark** for performance.
- **CI** (GitHub Actions running `./gradlew test lint`) and **ktlint/detekt**.
- Multi-module split (`:core:network`, `:core:database`, `:feature:home`, ...)
  once a single `:app` module starts feeling crowded - the package layout
  here (`presentation` / `domain` / `data`) maps 1:1 onto that split if you
  want to do it later.

## Design reference

Matching Figma-style mockups for these 5 screens (Home, Search, Detail,
Watchlist, Profile) were built earlier in this conversation as a Design
artifact, if you want the visual spec alongside the code.
