package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.dao.TimeEntriesDAO
import com.jetbrains.handson.httpapi.routes.registerTimeEntryRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }

    val dao = TimeEntriesDAO(Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver"))
    dao.init()

    registerTimeEntryRoutes(dao)
}
