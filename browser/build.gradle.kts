import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    listOf(
        js(),
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs()
    ).forEach { target ->
        target.browser {
            commonWebpackConfig {
                outputFileName = "browser.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    port = 8081
                    static = (static ?: mutableListOf()).apply {
                        add(project.projectDir.path)
                    }
                }
            }
        }
        target.binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":shared-client"))
            }
        }
        wasmJsMain {
            dependencies {
                // Browser APIs are in the JS stdlib but Wasm needs them from library as of Kotlin 2.1.0
                implementation(libs.kotlinx.browser)
            }
        }
    }
}
