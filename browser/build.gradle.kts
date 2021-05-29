import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("js")
}

kotlin {
    js {
        browser {
            runTask {
                outputFileName = "browser.js"
                devServer = KotlinWebpackConfig.DevServer(
                    static = mutableListOf("$buildDir/processedResources/Js/main"),
                    port = 8081
                )
            }
        }
        binaries.executable()
    }
}


dependencies {
    val coroutineVersion = "1.5.0"
    val ktorVersion = "1.6.0"

    implementation(project(":shared-client"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutineVersion")

    implementation("io.ktor:ktor-client-js:$ktorVersion")
    implementation("io.ktor:ktor-client-core-js:$ktorVersion")
    implementation("io.ktor:ktor-client-json-js:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-js:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-js:$ktorVersion")
}
