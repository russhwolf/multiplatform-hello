import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("kotlinx-serialization")
    application
}

val ktorVersion = "1.2.6"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":shared"))

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
