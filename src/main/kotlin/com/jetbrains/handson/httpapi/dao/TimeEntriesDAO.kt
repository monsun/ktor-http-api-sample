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
        val format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

        TimeEntries.insert {
            it[TimeEntries.time] = time
//            it[TimeEntries.timestamp] = format.parseLocalDateTime(timestamp)
            it[TimeEntries.id] = UUID.randomUUID()
        }
        Unit
    }

    override fun updateTimeEntry(id: String, time: String, timestamp: String, problems: List<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteTimeEntry(id: String) = transaction(db) {
//        TimeEntries.deleteWhere { TimeEntries.id eq id }
        Unit
    }

    override fun getTimeEntry(id: String): TimeEntry? {
        TODO("Not yet implemented")
    }

    override fun getAllTimeEntries(): List<TimeEntry> = transaction(db) {
        TimeEntries.selectAll().map {
            TimeEntry(
                id = it[TimeEntries.id].toString(),
                timestamp = it[TimeEntries.timestamp].toString(),
                time = it[TimeEntries.time]
            )
        }
    }


    override fun close() { }
}