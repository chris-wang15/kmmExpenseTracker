package com.tools.records.android.presentation.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.android.utils.categoryDataSource
import com.tools.records.domain.category.ExpenseCategory

@Composable
fun OutcomeSelectionView(onClick: (ExpenseCategory) -> Unit) {
    val dataSource = categoryDataSource
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Daily",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryList(
            categoryList = dataSource.allDailyCategories,
            onClick = onClick,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Activity",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryList(
            categoryList = dataSource.allActivityCategories,
            onClick = onClick
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Family",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryList(
            categoryList = dataSource.allFamilyCategories,
            onClick = onClick
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Other",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryList(
            categoryList = dataSource.allOtherCategories,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun OutcomeSelectionViewPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OutcomeSelectionView(onClick = {})
        }
    }
}