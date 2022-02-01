package com.revolna.plugins

import com.revolna.entities.TelemetryDraft
import com.revolna.repository.DBTelemetryRepo
import com.revolna.repository.TelemetryRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.configureRouting() {
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
//    install(ContentNegotiation) {
//        gson()
//    }
    val repository: TelemetryRepository = DBTelemetryRepo()
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello Deem World!")
        }

        get("/t") {
            call.respond(repository.getAllTelemetry())
        }

        post("/tls") {
            println(">>>  >> << <<<")
            val telemetryDraft = try {
                call.receive<TelemetryDraft>()

            } catch (e:Exception) {
                call.respond(HttpStatusCode.BadRequest,"Missing Fields")
                return@post
            }
            //respond to user
            var telemetry = repository.addTelemetry(telemetryDraft)

            println(">>>  >> ${telemetryDraft}")

            call.respond(telemetry)
        }


        delete("/del/") {
            //val todoId = call.parameters["id"]?.toIntOrNull()

            val todoId = try {
                call.request.queryParameters["id"]!!.toInt()
            } catch (e:Exception) {
                call.respond(HttpStatusCode.BadRequest,"QueryParameter:id is not present")
                return@delete
            }


            if (todoId == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "id parameter has to be a number!")
                return@delete
            }

            val removed = repository.removeTelemetry(todoId)
            if (removed) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound,
                    "found no todo with the id $todoId")
            }
        }
    }
    routing {
    }
}
