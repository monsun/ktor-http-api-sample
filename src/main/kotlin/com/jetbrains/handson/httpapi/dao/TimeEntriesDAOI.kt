package com.jetbrains.handson.httpapi.dao

import io.ktor.utils.io.core.*

interface TimeEntriesDAOI : Closeable {

    fun init()
    fun createTimeEntry(id: String, time: String, timestamp: String, problems: List<String> = listOf())
    fun updateTimeEntry(id: String, time: String, timestamp: String, problems: List<String> = listOf())
    fun deleteTimeEntry(id: String)
    fun getTimeEntry(id: String)
    fun getAllTimeEntries()

}