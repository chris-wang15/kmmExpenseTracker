package com.tools.records.android.presentation.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.android.utils.categoryImageMap
import com.tools.records.domain.category.CategoryDataSource
import com.tools.records.domain.category.ExpenseCategory

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryList(
    categoryList: List<ExpenseCategory>,
    onClick: (ExpenseCategory) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(categoryList.size) { index ->
            val category = categoryList[index]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .padding(4.dp)
                        .size(50.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(12.dp))
                        .combinedClickable(
                            onClick = { onClick(category) }
                        )
                ) {
                    Image(
                        painter = painterResource(id = categoryImageMap[category.id!!]!!),
                        contentDescription = "icon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .matchParentSize()
                            .padding(8.dp)
                    )
                }

                Text(
                    text = category.title,
                    color = Color.Black,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview
@Composable
fun CategoryListPreview() {
    val list = CategoryDataSource().allDailyCategories
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CategoryList(categoryList = list, onClick = {})
        }
    }
}