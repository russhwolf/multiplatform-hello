pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "MultiplatformHello"

include(":shared")
include(":shared-client")
include(":android")
include(":browser")
include(":server")
