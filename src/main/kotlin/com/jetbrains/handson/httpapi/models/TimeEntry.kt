package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class TimeEntry(val id: String, val measuredTime: String, val timeOfEntry: String, val problems: List<String> = listOf())

val timeEntryStorage = mutableListOf<TimeEntry>()