package com.tools.records.android.presentation.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.android.utils.AmountUtil
import com.tools.records.android.utils.categoryImageMap
import com.tools.records.domain.expense.Expense
import com.tools.records.domain.time.DateTimeUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ExpenseList(
    allExpense: StateFlow<List<Expense>>,
) {
    val expenseList = allExpense.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        expenseList.value.forEach { item: Expense ->
            item {
                ExpenseViewHolder(expense = item)
            }
        }
    }
}

@Composable
fun ExpenseViewHolder(expense: Expense) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Gray, shape = CircleShape) // 设置灰色圆形背景
        ) {
            Image(
                painter = painterResource(id = categoryImageMap[expense.categoryId]!!),
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = expense.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = AmountUtil.formatAmount(expense.cost),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = DateTimeUtil.formatNoteDate(expense.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }

}

@Preview
@Composable
fun ExpenseListPreview() {
    val allExpense = MutableStateFlow(
        listOf(
            Expense(
                id = 1,
                title = "t1t1t1t1t1t1",
                cost = 6.6,
                categoryId = 1,
                time = DateTimeUtil.now()
            ),
            Expense(
                id = 2,
                title = "t2t2t2t2t2t2",
                cost = 6.6,
                categoryId = 2,
                time = DateTimeUtil.now()
            ),
            Expense(
                id = 3,
                title = "t3t3t3t3t3t3t",
                cost = 6.6,
                categoryId = 3,
                time = DateTimeUtil.now()
            )
        )
    )
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExpenseList(allExpense = allExpense)
        }
    }
}