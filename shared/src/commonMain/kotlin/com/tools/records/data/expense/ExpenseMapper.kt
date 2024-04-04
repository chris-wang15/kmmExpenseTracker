package com.tools.records.data.expense

import com.tools.records.domain.expense.Expense
import database.ExpenseEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun ExpenseEntity.fromEntity(): Expense {
    return Expense(
        id = id,
        title = title,
        cost = cost,
        categoryId = categoryId,
        time = Instant
            .fromEpochMilliseconds(time)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}