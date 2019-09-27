import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

val serializationVersion = "0.13.0"

kotlin {
    jvm()

    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true
    val iosTarget = if (isDevice) {
        presets.getByName("iosArm64")
    } else {
        presets.getByName("iosX64")
    }
    targetFromPreset(iosTarget, "ios")

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }

        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val iosMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }
        val iosTest by getting {
            dependencies {
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.create("iosTest") {
    dependsOn("linkDebugTestIos")
    doLast {
        val testBinaryPath =
            (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getTest("DEBUG").outputFile.absolutePath
        exec {
            commandLine("xcrun", "simctl", "spawn", "iPhone Xʀ", testBinaryPath)
        }
    }
}
tasks["check"].dependsOn("iosTest")
