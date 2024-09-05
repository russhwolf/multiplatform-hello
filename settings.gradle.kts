rootProject.name = "MultiplatformHello"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":shared")
include(":shared-client")
include(":android")
include(":browser")
include(":server")
