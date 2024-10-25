package lab6.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/goodbye"){
            call.respondText { "Goodbye!" }
        }

        get("/msd"){
            call.respondText { "Hello MSD!" }
        }
    }
}
