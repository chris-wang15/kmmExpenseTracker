package com.tools.records.android.presentation.expense

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tools.records.domain.time.DateTimeUtil

@Composable
fun MonthPicker(selectedMonth: MutableState<Int>) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.clickable {
                isMenuExpanded = true
            },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = DateTimeUtil.formatMonth(selectedMonth.value),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            for (month in 1 until 12) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = DateTimeUtil.formatMonth(month),
                        )
                    },
                    onClick = {
                        selectedMonth.value = month
                        isMenuExpanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun MonthPickerPreview() {
    val selectedMonth = remember { mutableIntStateOf(1) }
    MonthPicker(selectedMonth = selectedMonth)
}