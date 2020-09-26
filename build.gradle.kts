plugins {
    kotlin("multiplatform") version "1.4.10" apply false
    kotlin("android") version "1.4.10" apply false
    id("kotlinx-serialization") version "1.4.0" apply false
    id("com.android.application") version "4.0.1" apply false
    id("com.android.library") version "4.0.1" apply false
}

allprojects {
    repositories {
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
