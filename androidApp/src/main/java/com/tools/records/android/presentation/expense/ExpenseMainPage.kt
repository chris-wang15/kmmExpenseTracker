package com.tools.records.android.presentation.expense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.domain.expense.Expense
import com.tools.records.domain.time.DateTimeUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExpenseMainPage(
    allExpense: StateFlow<List<Expense>>,
    navigateToCategory: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column {
            ExpenseTitle()
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseList(allExpense = allExpense)
        }

        FloatingActionButton(
            onClick = {
                navigateToCategory()
            },
            modifier = Modifier.padding(16.dp),
            shape = CircleShape,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

@Preview
@Composable
fun ExpenseMainPagePreview() {
    val allExpense = MutableStateFlow(
        listOf(
            Expense(id = 1, title = "t1", cost = 6.6, categoryId = 1, time = DateTimeUtil.now()),
            Expense(id = 2, title = "t2", cost = 6.6, categoryId = 2, time = DateTimeUtil.now()),
            Expense(id = 3, title = "t3", cost = 6.6, categoryId = 3, time = DateTimeUtil.now())
        )
    )
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExpenseMainPage(allExpense = allExpense, navigateToCategory = {})
        }
    }
}
