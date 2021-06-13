package com.jetbrains.handson.httpapi.dao

import com.jetbrains.handson.httpapi.models.TimeEntry
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TimeEntriesDAO(val db: Database) : TimeEntriesDAOI {
    override fun init() = transaction(db) {
        SchemaUtils.create(TimeEntries, Problems)
    }

    override fun createTimeEntry(id: String, time: String, timestamp: String, problems: List<String>) {
        TODO("Not yet implemented")
    }

    override fun updateTimeEntry(id: String, time: String, timestamp: String, problems: List<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteTimeEntry(id: String) {
        TODO("Not yet implemented")
    }

    override fun getTimeEntry(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllTimeEntries() {
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