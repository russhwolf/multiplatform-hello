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
import io.ktor.serialization.json

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused", "UNUSED_PARAMETER") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }

    routing {
        get("/message") {
            call.respond(message)
        }
    }
}

private val message = Message("hello")
