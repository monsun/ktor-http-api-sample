package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.dao.TimeEntriesDAO
import com.jetbrains.handson.httpapi.models.TimeEntry
import com.jetbrains.handson.httpapi.models.timeEntryStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.timeEntryRouting(dao: TimeEntriesDAO) {
    route("/timeEntry") {
        get {
            val timeEntries = dao.getAllTimeEntries()
            if (timeEntries.isNotEmpty()) {
                call.respond(timeEntries)
            } else {
                call.respondText("No time entries found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val entry = dao.getTimeEntry(id) ?: return@get call.respondText(
                "No time entry with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(entry)
        }

        post {
            val timeEntry = call.receive<TimeEntry>()
            dao.createTimeEntry(timeEntry.time, timeEntry.timestamp, timeEntry.problems)
            call.respondText("Time entry stored correctly", status = HttpStatusCode.Created)
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            dao.deleteTimeEntry(id)
        }
    }
}

fun Application.registerTimeEntryRoutes(dao: TimeEntriesDAO) {
    routing {
        timeEntryRouting(dao)
    }
}