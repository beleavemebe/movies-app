# Movies App
An extremely simple client app for NY Times movie reviews API.

## Footage
https://user-images.githubusercontent.com/79194789/166585193-1298c073-f631-4839-95d3-4f0eec51986f.mp4

## Architecture & Tech Stack
- [x] MVVM+ architecture with <a href="https://github.com/orbit-mvi/orbit-mvi">Orbit</a> redux/MVI-like library
- [x] <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html">Coroutines</a> & <a href="https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/">Flow</a> for asynchronous and more
- [x] Navigation between fragments via <a href="https://developer.android.com/guide/navigation/navigation-getting-started">NavComponent</a>
- [x] Dependency Injection with <a href="https://dagger.dev/hilt/">Hilt</a>
- [x] Network requests via <a href="https://square.github.io/retrofit/">Retrofit</a>
- [x] Pagination with <a href="https://developer.android.com/topic/libraries/architecture/paging/v3-overview">Jetpack Paging 3</a>
- [x] Placeholders via <a href="https://facebook.github.io/shimmer-android/">ShimmerLayout</a>
- [ ] Local cache with <a href="https://developer.android.com/jetpack/androidx/releases/room">Room</a> persistence library
