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

                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentNegotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.client.logging)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))

                implementation(libs.kotlinx.coroutines.test)

                implementation(libs.ktor.client.mock)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.android)

                implementation(libs.ktor.client.android)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.client.ios)

                implementation(libs.kotlinx.coroutines.core)
            }
        }
        jsMain {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.ktor.client.js)
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

