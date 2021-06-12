package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
//TODO: timesamp
data class TimeEntry(val id: String, val time: String, val timestamp: String, val problems: List<String> = listOf())

val timeEntryStorage = mutableListOf<TimeEntry>()