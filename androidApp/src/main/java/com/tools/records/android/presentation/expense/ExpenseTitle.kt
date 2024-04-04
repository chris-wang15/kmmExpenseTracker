package com.tools.records.android.presentation.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.android.utils.AmountUtil
import com.tools.records.data.di.DatabaseModel
import com.tools.records.domain.time.DateTimeUtil

@Composable
fun ExpenseTitle() {
    val whiteFont = TextStyle(
        color = Color.White,
    )

    val nowDate = DateTimeUtil.now()
    val selectedYear = remember { mutableIntStateOf(nowDate.year) }
    val selectedMonth = remember { mutableIntStateOf(nowDate.monthNumber) }
    val outcomeAmount = remember { mutableDoubleStateOf(0.0) }
    val incomeAmount = remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(selectedYear.intValue, selectedMonth.intValue) {
        val year = selectedYear.intValue
        val month = selectedMonth.intValue
        outcomeAmount.doubleValue = DatabaseModel.expenseDataSource.getOutcomeByDate(
            year = year, month = month
        )
        incomeAmount.doubleValue = DatabaseModel.expenseDataSource.getIncomeByDate(
            year = year, month = month
        )
    }

    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4285F4),
                        Color(0x804285F4)
                    ),
                    startY = 0f,
                    endY = 200f
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )

            Text(
                text = "EXPENSE RECORDS",
                style = MaterialTheme.typography.titleLarge.merge(whiteFont)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                ) {
                    YearPicker(selectedYear = selectedYear)
                    MonthPicker(selectedMonth = selectedMonth)

                }

                Spacer(modifier = Modifier.width(16.dp))

                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Row(
                        modifier = Modifier,
                    ) {
                        Text(
                            text = "Income",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "Outcome",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                    }

                    Row(
                        modifier = Modifier,
                    ) {
                        Text(
                            text = AmountUtil.formatAmount(incomeAmount.doubleValue),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = AmountUtil.formatAmount(outcomeAmount.doubleValue),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ExpenseTitlePreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExpenseTitle()
            Spacer(modifier = Modifier)
        }
    }
}