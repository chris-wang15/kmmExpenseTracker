package com.tools.records.data.di

import android.app.Application
import com.tools.records.data.expense.ExpenseDataSourceImpl
import com.tools.records.data.local.DatabaseDriverFactory
import com.tools.records.database.MainDatabase
import com.tools.records.domain.expense.ExpenseDataSource

object DatabaseModel {
    lateinit var expenseDataSource: ExpenseDataSource
    fun initDatabase(app: Application) {
        val driver = DatabaseDriverFactory(app).createDriver()
        val database = MainDatabase.Companion(driver)
        expenseDataSource = ExpenseDataSourceImpl(database.expenseQueries)
    }
}