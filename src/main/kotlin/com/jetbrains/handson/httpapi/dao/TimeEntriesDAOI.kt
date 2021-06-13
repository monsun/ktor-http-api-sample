package com.jetbrains.handson.httpapi.dao

import com.jetbrains.handson.httpapi.models.TimeEntry
import io.ktor.utils.io.core.*

interface TimeEntriesDAOI : Closeable {

    fun init()
    fun createTimeEntry(time: String, timestamp: String, problems: List<String> = listOf())
    fun updateTimeEntry(id: String, time: String, timestamp: String, problems: List<String> = listOf())
    fun deleteTimeEntry(id: String)
    fun getTimeEntry(id: String): TimeEntry?
    fun getAllTimeEntries(): List<TimeEntry>

}