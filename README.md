# Posts Demo: Native Android with Kotlin & Jetpack Compose

A native Android app that loads the latest spaceflight news from the public [Spaceflight News API](https://api.spaceflightnewsapi.net) and shows it in a Material 3 feed. The app is small on purpose. It exists to show a clean, modern Android architecture end to end, from the HTTP client to the Compose UI.

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-Material_3-4285F4?logo=jetpackcompose&logoColor=white)
![Android](https://img.shields.io/badge/Android-minSdk_24-3DDC84?logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-Moshi-48B983)

## Features

- Feed of the 20 newest articles (`LazyColumn` with stable keys): image cards loaded with Coil, title and source
- Pull-to-refresh (`PullToRefreshBox`)
- Full set of UI states: loading spinner, empty state, a full-screen error with **Retry**, and a snackbar when a refresh fails while posts are already on screen
- HTTP logging only in debug builds (`BuildConfig.DEBUG`)

## Architecture

Clean architecture with MVVM and unidirectional data flow:

```
ui/        PostsScreen (Compose) ← collects StateFlow<PostsUiState> ← PostsViewModel
domain/    Post model · PostRepository interface · Resource<Success | Error>
data/      SpaceflightNewsApi (Retrofit) → DTOs → PostMapper → PostRepositoryImpl
di/        ServiceLocator: lazily wires OkHttp, Moshi, Retrofit and the repository
```

- **ViewModel + StateFlow.** A single immutable `PostsUiState` is updated with `update { copy(...) }` and collected with `collectAsStateWithLifecycle()`.
- **Repository behind an interface.** The ViewModel depends on `PostRepository` only, so the data source can be swapped or faked in tests.
- **DTO → domain mapping.** Network models never reach the UI.
- **`Resource` wrapper.** Errors become values, not exceptions, so the UI decides how to present them.
- **Manual DI** via a `ServiceLocator` and a `viewModelFactory`. It is small and readable, and it is a natural place to introduce Hilt later.

## Tech stack

Kotlin · Jetpack Compose · Material 3 · AndroidX Lifecycle (ViewModel, `collectAsStateWithLifecycle`) · Kotlin Coroutines & StateFlow · Retrofit · Moshi · OkHttp (logging interceptor) · Coil · Gradle Kotlin DSL with a version catalog

## Run it

1. Open the project in Android Studio.
2. Let Gradle sync.
3. Run the `app` configuration on an emulator or device (Android 7.0+, API 24).

No API key is needed.

## Roadmap

- Unit tests for `PostsViewModel` and `PostRepositoryImpl` with a fake repository and `kotlinx-coroutines-test`
- Compose UI tests for the loading, error and empty states
- Offline cache with Room, and a details screen with Navigation Compose
- Hilt for dependency injection

## Author

**Samuel Abayizera** · [GitHub](https://github.com/911samuel)
