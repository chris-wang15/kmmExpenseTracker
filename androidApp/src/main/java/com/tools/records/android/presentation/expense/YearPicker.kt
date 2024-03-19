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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@Composable
fun YearPicker(selectedYear: MutableState<Int>) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.clickable {
                isMenuExpanded = true
            },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${selectedYear.value}",
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
            val endYear = Calendar.getInstance().get(Calendar.YEAR)
            val startYear = endYear - 5
            for (year in startYear..endYear) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = year.toString(),
                        )
                    },
                    onClick = {
                        selectedYear.value = year
                        isMenuExpanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun YearPickerPreview() {
    val selectedYear = remember { mutableStateOf(2022) }
    YearPicker(selectedYear = selectedYear)
}