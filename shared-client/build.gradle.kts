import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)

    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        iosX64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
        }
    }

    listOf(
        js(),
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs()
    ).forEach {
        it.browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":shared"))

                api(libs.kotlinx.coroutines.core)

                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.serialization)
                api(libs.ktor.client.logging)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))

                api(libs.kotlinx.coroutines.test)

                implementation(libs.ktor.client.mock)
            }
        }
        androidMain {
            dependencies {
                api(libs.kotlinx.coroutines.android)

                api(libs.ktor.client.android)
            }
        }
        iosMain {
            dependencies {
                api(libs.ktor.client.ios)

                api(libs.kotlinx.coroutines.core)
            }
        }
        jsMain {
            dependencies {
                api(libs.ktor.client.js)
            }
        }
        wasmJsMain {
            dependencies {
                api(libs.ktor.client.js)
            }
        }
    }
}

android {
    namespace = "com.example.multiplatform.shared"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

