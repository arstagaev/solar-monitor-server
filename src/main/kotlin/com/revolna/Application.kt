package com.revolna

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.revolna.plugins.*

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)

}
