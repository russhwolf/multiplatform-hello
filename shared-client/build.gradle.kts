import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

val coroutineVersion = "1.5.0-native-mt"
val ktorVersion = "1.6.0"

kotlin {
    android("android")

    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true
    val ios: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = if (isDevice) ::iosArm64 else ::iosX64
    ios("ios") {
        binaries {
            framework {
                baseName = "Shared"
                embedBitcode = BITCODE
                transitiveExport = true
            }
        }
    }

    js("js") {
        browser()
    }

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
            }
        }

        commonMain {
            dependencies {
                implementation(project(":shared"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-json:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
                api("io.ktor:ktor-client-logging:$ktorVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

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
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))

                implementation("androidx.test:core:1.3.0")
                implementation("androidx.test.ext:junit:1.1.2")
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
                implementation(kotlin("test-js"))
            }
        }
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(15)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val targetName = "ios"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)
