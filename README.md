# MultiplatformHello

A full-stack hello world for Kotlin Multiplatform

## Project Structure

This project consists of four gradle modules as well as an xcode project. It was developed using Intellij IDEA but is probably usable from Android Studio as well.

### Shared

This is the central module which is included in both server and client. It consists of a `Message` class which is serializable by `kotlinx-serialization`.

### Server

This is a simple Ktor server running on the Netty engine with a single endpoint `/message`, which outputs a `Message` object serialized to JSON.

The gradle task `run` will deploy the server to localhost on port 8080. 

### Shared-Mobile

This consumes the `shared` module and contains shared mobile code. It includes an `ApiClient` class with a method to query the `/message` endpoint using the Ktor http client, as well as a top-level function `hello()` which provides a callback interface and rudimentary error-handling.

### Android

This is an Android project consuming the `:shared-mobile` module. It contains a single activity which calls the `hello()` function and displays its output in UI.

The Android app can be built by creating and running an Android run configuration in IDEA

### iOS

The iOS code lives in `shared-mobile/xcodeproj`. It makes use of the `xcode-compat` gradle plugin which generates the gradle task needed to sync the `:shared-mobile` output framework to Xcode. 

It includes a ViewController which calls the `hello()` function and renders its output to screen. 

The iOS code can be modified or built by opening `shared-mobile/xcodeproj/Shared.xcodeproj` in Xcode.

### Tests

Unit tests exist for the `:shared`, `:shared-mobile`, and `:server` modules. They can be run using the gradle task `check`. You may need to `clean` as well in order to re-run tests if no code-changes have happened.

## Known issues

- The `iosTest` task in `shared-mobile` is currently commented-out due to build errors.
- The IDE doesn't recognize that `:shared` is on the `:server` classpath.
- The IDE has trouble recognizing the generated `R` class in the `:android` module.
