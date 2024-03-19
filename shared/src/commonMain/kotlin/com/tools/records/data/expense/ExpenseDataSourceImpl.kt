package com.tools.records.data.expense

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.tools.records.CommonFlow
import com.tools.records.asCommonFlow
import com.tools.records.domain.expense.Expense
import com.tools.records.domain.expense.ExpenseDataSource
import com.tools.records.domain.time.DateTimeUtil
import database.ExpenseEntity
import database.ExpenseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class ExpenseDataSourceImpl(private val queries: ExpenseQueries) : ExpenseDataSource {
    override suspend fun insertExpense(expense: Expense) {
        queries.insertExpense(
            id = expense.id,
            title = expense.title,
            cost = expense.cost,
            categoryId = expense.categoryId,
            time = DateTimeUtil.toEpochMillis(expense.time)
        )
    }

    override suspend fun getExpenseById(id: Long): Expense? {
        return queries.getExpenseById(id)
            .executeAsOneOrNull()
            ?.fromEntity()
    }

    override suspend fun getExpenseByCategory(categoryId: Long): List<Expense> {
        return queries.getExpenseByCategory(categoryId)
            .executeAsList()
            .map(ExpenseEntity::fromEntity)
    }

    override suspend fun getAllExpense(): List<Expense> {
        return queries.getAllExpense()
            .executeAsList()
            .map(ExpenseEntity::fromEntity)
    }

    override fun observeAllExpense(): CommonFlow<List<Expense>> {
        return queries.getAllExpense()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map {
                it.map(ExpenseEntity::fromEntity)
            }
            .asCommonFlow()
    }

    override suspend fun deleteExpenseById(id: Long) {
        queries.deleteExpenseById(id)
    }

    override suspend fun getOutcomeByDate(year: Int, month: Int): Double {
        return queries.getOutcomeBydate("$year-${DateTimeUtil.addZeroIfSmall(month)}")
            .executeAsOneOrNull()?.totalCost ?: 0.0
    }

    override suspend fun getIncomeByDate(year: Int, month: Int): Double {
        return queries.getIncomeBydate("$year-${DateTimeUtil.addZeroIfSmall(month)}")
            .executeAsOneOrNull()?.totalCost ?: 0.0
    }
}