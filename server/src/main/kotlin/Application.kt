package com.example.multiplatform.server

import com.example.multiplatform.shared.Message
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.serialization

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused", "UNUSED_PARAMETER") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        serialization()
    }
    install(CORS) {
        anyHost()
        allowNonSimpleContentTypes = true
    }

    routing {
        get("/message") {
            call.respond(message)
        }
    }
}

@Suppress("UNRESOLVED_REFERENCE") // IDE has trouble seeing import from Shared from jvm-only module
private val message = Message("hello")
