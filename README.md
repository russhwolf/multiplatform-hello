# Multiplatform Hello

A full-stack hello world for Kotlin Multiplatform

## Project Structure

This project consists of several gradle modules as well as an xcode project. It was developed using Intellij IDEA but is probably usable from Android Studio as well.

### Shared

This is the central module which is included in both server and client. It consists of a `Message` class which is serializable by `kotlinx-serialization`.

### Server

This is a simple Ktor server running on the Netty engine with a single endpoint `/message`, which outputs a `Message` object serialized to JSON.

Running `./gradlew server:run` will deploy the server to localhost on port 8080. 

### Shared-Client

This consumes the `shared` module and contains shared client code. It includes an `ApiClient` class with a method to query the `/message` endpoint using the Ktor http client, as well as a top-level function `hello()` which provides a callback interface and rudimentary error-handling.

### Android

This is an Android project consuming the `:shared-mobile` module. It contains a single activity which calls the `hello()` function and displays its output in UI.

The Android app can be built by creating and running an Android run configuration in IDEA.

Because the server deploys to localhost, it's recommended to only run the app through the emulator. There is configuration to ensure that the emulator sees the local server deploy which will not work on-device.

### iOS

The iOS code lives in the `ios` directory. It includes a ViewController which calls the `hello()` function and renders its output to screen. 

The iOS code can be modified or built by opening `ios/Multiplatform Hello.xcodeproj` in Xcode.

Because the server deploys to localhost, it's recommended to only run the app through the simulator, but there is gradle configuration for device targets as well.

### Browser

There is a browser client available in the `:browser` module. It contains a simple page which calls `hello()` and updates the page body with the output.

The browser client can be deployed with `./gradlew browser:browserRun`. It will deploy to localhost on port 8081 to avoid interfering with the server on 8080.

### Tests

Unit tests exist for the `:shared`, `:shared-client`, and `:server` modules. They can be run using the gradle task `check`. You may need to `clean` as well in order to re-run tests if no code-changes have happened.

## Known issues

- The IDE doesn't recognize that `:shared` is on the `:server` classpath. See https://youtrack.jetbrains.com/issue/KT-29082.
- The browser client seems to have trouble in Firefox due to CORS issues, but has been verified to work in Chrome.
