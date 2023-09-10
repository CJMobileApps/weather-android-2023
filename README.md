# Weather

This is an Android app that displays the current weather. Uses [OpenWeather](https://openweathermap.org/) APIs

Build Tools & Versions Used
----

*Android Studio:* Flamingo | 2022.2.1<br />
*Android Min SDK Version:* 26 <br />
*Android Target SDK Version:* 34

API Key
----
Replace the `APPID` in the app module `build.gradle` file with a valid API key from [OpenWeather](https://openweathermap.org/)

TODO | Future Features
----
* [ ] Better location handling. What if user doesn't have last location?
* [ ] Request current location updates periodically and update data. 
* [ ] Location foreground service?
* [ ] Android Compose Ui Previews
* [ ] Dark Mode
* [ ] Better theming overall
* [ ] Differentiate network cacheing that doesn't need to be new. Like City locations don't change often so the cache for that can be longer when making a network call.
* [ ] Add pull down refresh.
* [ ] Extract strings to resource file
* [ ] Test more edge cases and add more error handling.
* [ ] Add TopBar With Back Button to Search Screen
* [ ] Edit Text Debounce
* [ ] Add more icons for buttons
* [ ] Make app more accessible / Usability
* [ ] When someone denies locations show a disclaimer why it should be allowed.
* [ ] Add more Unit Test. You can never have enough Unit Test.
* [ ] Need a Ui designer to make it more pretty.

Dependencies
---

**Libraries**
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
- [Jetpack Navigation](https://developer.android.com/guide/navigation) - Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
-[Compose UI Test](https://developer.android.com/jetpack/compose/testing) - Compose provides a set of testing APIs to find elements, verify their attributes and perform user actions. They also include advanced features such as time manipulation.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Asynchronous or non-blocking programming is an important part of the development landscape. When creating server-side, desktop, or mobile applications, it's important to provide an experience that is not only fluid from the user's perspective, but also scalable when needed.
- [Retrofit](http://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Dagger Hilt](https://dagger.dev/hilt/) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java applications.
- [Mockito](https://github.com/mockito/mockito) - Most popular Mocking framework for unit tests written in Java.
- [JUnit 5](https://junit.org/junit5/) - JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM. This includes focusing on Java 8 and above, as well as enabling many different styles of testing.
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.
- [Room](https://developer.android.com/training/data-storage/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

Screen shots and Video Walkthrough
----
