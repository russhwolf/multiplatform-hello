plugins {
    kotlin("multiplatform") version "1.3.50" apply false
    kotlin("android") version "1.3.50" apply false
    id("kotlinx-serialization") version "1.3.50" apply false
    id("com.android.application") version "3.5.1" apply false
    id("com.android.library") version "3.5.1" apply false
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

    configurations.create("compileClasspath")
}
