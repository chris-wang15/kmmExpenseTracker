package com.tools.records.domain.time

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun parseLong(epochMillis: Long): LocalDateTime {
        return Instant
            .fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun parseLongToDate(epochMillis: Long): String {
        val dateTime = parseLong(epochMillis)
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = addZeroIfSmall(dateTime.dayOfMonth)
        val year = dateTime.year
        return buildString {
            append(day)
            append(" ")
            append(month)
            append(" ")
            append(year)
        }
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatNoteDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = addZeroIfSmall(dateTime.dayOfMonth)
        val year = dateTime.year

        return buildString {
            append(day)
            append(" ")
            append(month)
            append(" ")
            append(year)
        }
    }

    fun addZeroIfSmall(time: Int): String {
        return if (time < 10) "0$time" else time.toString()
    }

    fun formatMonth(monthNumber: Int): String {
        return when (monthNumber) {
            1 -> "JAN"
            2 -> "FEB"
            3 -> "MAR"
            4 -> "APR"
            5 -> "MAY"
            6 -> "JUN"
            7 -> "JUL"
            8 -> "AUG"
            9 -> "SEP"
            10 -> "OCT"
            11 -> "NOV"
            12 -> "DEC"
            else -> "Invalid month"
        }
    }
}