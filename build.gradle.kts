plugins {
    kotlin("multiplatform") version "1.4-M2" apply false
    kotlin("android") version "1.4-M2" apply false
    id("kotlinx-serialization") version "1.4-M2" apply false
    id("com.android.application") version "3.6.2" apply false
    id("com.android.library") version "3.6.2" apply false
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
