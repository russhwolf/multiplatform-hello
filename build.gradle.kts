plugins {
    kotlin("multiplatform") version "1.4.0-rc" apply false
    kotlin("android") version "1.4.0-rc" apply false
    id("kotlinx-serialization") version "1.4.0-rc" apply false
    id("com.android.application") version "4.0.1" apply false
    id("com.android.library") version "4.0.1" apply false
}

allprojects {
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        google()
        mavenCentral()
        jcenter()
    }

    tasks.withType(AbstractTestTask::class) {
        testLogging {
            showStandardStreams = true
            events("passed", "failed")
        }
    }
}
