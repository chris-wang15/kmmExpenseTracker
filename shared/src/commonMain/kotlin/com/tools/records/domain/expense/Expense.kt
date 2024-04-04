package com.tools.records.domain.expense

import kotlinx.datetime.LocalDateTime

data class Expense(
    val id: Long?,
    val title: String,
    val cost: Double,
    val categoryId: Long,
    val time: LocalDateTime,
)