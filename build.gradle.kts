plugins {
    kotlin("multiplatform") version "1.5.10" apply false
    kotlin("android") version "1.5.10" apply false
    id("kotlinx-serialization") version "1.5.10" apply false
    id("com.android.application") version "4.1.2" apply false
    id("com.android.library") version "4.1.2" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType(AbstractTestTask::class) {
        testLogging {
            showStandardStreams = true
            events("passed", "failed")
        }
    }
}
