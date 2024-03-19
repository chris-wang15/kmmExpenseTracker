package com.tools.records.domain.expense

import com.tools.records.CommonFlow

interface ExpenseDataSource {
    suspend fun insertExpense(expense: Expense)
    suspend fun getExpenseById(id: Long): Expense?
    suspend fun getExpenseByCategory(categoryId: Long): List<Expense>
    suspend fun getAllExpense(): List<Expense>
    fun observeAllExpense(): CommonFlow<List<Expense>>
    suspend fun deleteExpenseById(id: Long)
    suspend fun getOutcomeByDate(year: Int, month: Int): Double
    suspend fun getIncomeByDate(year: Int, month: Int): Double
}