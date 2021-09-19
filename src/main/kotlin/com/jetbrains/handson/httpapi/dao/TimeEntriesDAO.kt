package com.jetbrains.handson.httpapi.dao

import com.jetbrains.handson.httpapi.models.TimeEntry
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.format.DateTimeFormat
import java.util.*


class TimeEntriesDAO(val db: Database) : TimeEntriesDAOI {
    override fun init() = transaction(db) {
        SchemaUtils.create(TimeEntries, Problems)
    }

    override fun createTimeEntry(time: String, timestamp: String, problems: List<String>) = transaction(db) {
        val format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")
        val uuid = UUID.randomUUID()

        TimeEntries.insert {
            it[measuredTime] = time
            it[timeOfEntry] = format.parseDateTime(timestamp).toDateTime()
            it[TimeEntries.id] = uuid
        }
        for (problem in problems) {
            Problems.insert {
                it[name] = problem
                it[entryId] = uuid
            }
        }
    }

    override fun updateTimeEntry(id: String, time: String, timestamp: String, problems: List<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteTimeEntry(id: String) = transaction(db) {
//        TimeEntries.deleteWhere { TimeEntries.id eq id }
    }

    override fun getTimeEntry(id: String): TimeEntry? {
        TODO("Not yet implemented")
    }

    override fun getAllTimeEntries(): List<TimeEntry> = transaction(db) {
        TimeEntries.selectAll().map {
            TimeEntry(
                id = it[TimeEntries.id].toString(),
                timeOfEntry = it[TimeEntries.timeOfEntry].toString(),
                measuredTime = it[TimeEntries.measuredTime],
            )
        }
    }


    override fun close() { }
}