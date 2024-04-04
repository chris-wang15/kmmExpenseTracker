package com.tools.records.android.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tools.records.data.di.DatabaseModel
import com.tools.records.domain.expense.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    private val expenseDataSource = DatabaseModel.expenseDataSource

    val allExpense: StateFlow<List<Expense>> = expenseDataSource.observeAllExpense()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
}