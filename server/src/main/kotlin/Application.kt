package com.example.multiplatform.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import kotlinx.serialization.Serializable

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused", "UNUSED_PARAMETER") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        serialization()
    }

    routing {
        get("/message") {
            call.respond(message)
        }
    }
}

private val message = Message("hello")


// TODO something's missing in gradle and we're not picking up common code from :shared. Duplicating this here for now.
@Serializable
data class Message(val value: String)
