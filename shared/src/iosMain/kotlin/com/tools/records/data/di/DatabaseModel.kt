package com.tools.records.data.di

import com.tools.records.data.expense.ExpenseDataSourceImpl
import com.tools.records.data.local.DatabaseDriverFactory
import com.tools.records.database.MainDatabase
import com.tools.records.domain.expense.ExpenseDataSource

class DatabaseModel {
    lateinit var expenseDataSource: ExpenseDataSource

    fun doInitDatabase() {
        val driver = DatabaseDriverFactory().createDriver()
        val database = MainDatabase.Companion(driver)
        expenseDataSource = ExpenseDataSourceImpl(database.expenseQueries)
    }
}