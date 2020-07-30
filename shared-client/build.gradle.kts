import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

val coroutineVersion = "1.3.8-1.4.0-rc"
val ktorVersion = "1.3.2-1.4.0-rc"

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
                implementation(kotlin("stdlib-common"))
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
                implementation(kotlin("stdlib"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
                api("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))

                implementation("androidx.test:core:1.2.0")
                implementation("androidx.test.ext:junit:1.1.1")
            }
        }
        val iosMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                api("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosTest by getting {
            dependencies {
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))

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

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(15)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

// Adapted from Jetbrains xcode-compat plugin
kotlin.targets.withType<KotlinNativeTarget>().configureEach {
    binaries.withType<Framework>().configureEach {
        val buildType = NativeBuildType.valueOf(
            System.getenv("CONFIGURATION")?.toUpperCase()
                ?: "DEBUG"
        )
        if (this.buildType == buildType) {
            var dsymTask: Sync? = null

            if (buildType == NativeBuildType.DEBUG) {
                dsymTask = project.task<Sync>("buildForXcodeDSYM") {
                    dependsOn(linkTask)
                    val outputFile = linkTask.outputFile.get()
                    val outputDSYM = File(outputFile.parent, outputFile.name + ".dSYM")
                    from(outputDSYM)
                    into(File(System.getenv("CONFIGURATION_BUILD_DIR"), outputDSYM.name))
                }
            }

            val buildForXcodeTask = project.task<Sync>("buildForXcode") {
                dependsOn(dsymTask ?: linkTask)
                val outputFile = linkTask.outputFile.get()
                from(outputFile)
                into(File(System.getenv("CONFIGURATION_BUILD_DIR"), outputFile.name))
            }
        }
    }
}
