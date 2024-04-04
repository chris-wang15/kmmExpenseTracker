package com.tools.records.android.presentation.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.android.utils.AmountUtil
import com.tools.records.android.utils.categoryDataSource
import com.tools.records.data.di.DatabaseModel
import com.tools.records.domain.category.ExpenseCategory
import com.tools.records.domain.expense.Expense
import com.tools.records.domain.time.DateTimeUtil
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    expenseCategory: ExpenseCategory,
    dismiss: () -> Unit,
) {
    val dataSource = DatabaseModel.expenseDataSource
    val isOutcome = categoryDataSource.isOutcome(expenseCategory.id)
    val initDate: Long = DateTimeUtil.toEpochMillis(DateTimeUtil.now())
    var amountValue by remember { mutableDoubleStateOf(0.0) }
    var title by remember { mutableStateOf(expenseCategory.title) }
    var isDialogShowing by remember { mutableStateOf(false) }
    val selectedDate = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = initDate,
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "New $title",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedDate.selectedDateMillis?.let {
                    DateTimeUtil.parseLongToDate(it)
                } ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = { isDialogShowing = true } // show date dialog
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendar")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    scope.launch {
                        dataSource.insertExpense(
                            Expense(
                                id = null,
                                title = title,
                                cost = amountValue,
                                categoryId = expenseCategory.id!!,
                                time = selectedDate.selectedDateMillis?.let(DateTimeUtil::parseLong)
                                    ?: DateTimeUtil.now()
                            )
                        )
                        dismiss()
                    }
                }
            ) {
                Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Calendar")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        AmountInputField(isOutcome) {
            amountValue = it
        }
        Spacer(modifier = Modifier.height(100.dp))

    }

    if (isDialogShowing) {
        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(
                    onClick = {
                        isDialogShowing = false
                    },
                ) {
                    Text("Accept")
                }
            },
        ) {
            DatePicker(state = selectedDate)
        }
    }
}

@Composable
fun AmountInputField(isOutcome: Boolean, onAmountChanged: (Double) -> Unit) {
    var amount by remember { mutableStateOf("") }

    OutlinedTextField(
        value = amount,
        onValueChange = { newAmount ->
            amount = newAmount
            try {
                var doubleValue: Double = AmountUtil.roundAmount(newAmount.toDouble())
                doubleValue = if (isOutcome) {
                    -abs(doubleValue)
                } else {
                    abs(doubleValue)
                }
                onAmountChanged(doubleValue)
            } catch (e: NumberFormatException) {
                // Ignore invalid input
            }
        },
        // TODO visualTransformation = CurrencyVisualTransformation("USD"),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Amount") },
    )
}

@Preview
@Composable
fun CategoryBottomSheetPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CategoryBottomSheet(expenseCategory = ExpenseCategory(1, "Title"), dismiss = {})
        }
    }
}