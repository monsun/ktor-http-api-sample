package com.jetbrains.handson.httpapi.dao

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.CurrentDateTime
import org.jetbrains.exposed.sql.jodatime.datetime

object TimeEntries: UUIDTable () {
    val timeOfEntry = datetime("timestamp").defaultExpression(CurrentDateTime())
    val measuredTime = varchar("time", 10)
    override val primaryKey = PrimaryKey(id, name = "PK_UUID_ENTRIES")
}


object Problems: UUIDTable() {
    val entryId = uuid("entryId").references(TimeEntries.id)
    val name = varchar("name", 60)

    override val primaryKey = PrimaryKey(entryId, name, name = "PK_PROBLEM_UID_JOIN")
}

fun main() {

}