plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

val ktorVersion = "2.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(project(":shared"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.2.11")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

