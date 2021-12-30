plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

val coroutineVersion = "1.6.0"
val ktorVersion = "2.0.0-beta-1"

kotlin {
    android()

    ios {
        binaries {
            framework {
                baseName = "Shared"
            }
        }
    }

    js {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":shared"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                api("io.ktor:ktor-client-logging:$ktorVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

                implementation("io.ktor:ktor-client-mock:$ktorVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

                api("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                api("io.ktor:ktor-client-ios:$ktorVersion")

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion") {
                    version {
                        strictly(coroutineVersion)
                    }
                }
            }
        }
        val iosTest by getting {
            dependencies {
            }
        }
        val jsMain by getting {
            dependencies {
                api("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        val jsTest by getting {
            dependencies {
            }
        }
    }
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 15
    }
}

