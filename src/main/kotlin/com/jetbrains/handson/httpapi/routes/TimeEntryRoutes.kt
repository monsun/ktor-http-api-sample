package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.TimeEntry
import com.jetbrains.handson.httpapi.models.timeEntryStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.timeEntryRouting() {
    route("/timeEntry") {
        get {
            if (timeEntryStorage.isNotEmpty()) {
                call.respond(timeEntryStorage)
            } else {
                call.respondText("No time entries found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val entry = timeEntryStorage.find { it.id == id } ?: return@get call.respondText(
                "No time entry with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(entry)
        }

        post {
            val timeEntry = call.receive<TimeEntry>()
            timeEntryStorage.add(timeEntry)
            call.respondText("Time entry stored correctly", status = HttpStatusCode.Created)
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (timeEntryStorage.removeIf {it.id == id }) {
                call.respondText("Customer removed successfully ")
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerTimeEntryRoutes() {
    routing {
        timeEntryRouting()
    }
}