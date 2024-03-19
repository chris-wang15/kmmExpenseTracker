package com.tools.records.android.presentation.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tools.records.android.MyApplicationTheme
import com.tools.records.domain.category.ExpenseCategory
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryMainPage(
    navigateBack: () -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val selectedTabIndex = remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(ExpenseCategory(1, "")) }

    val onCategoryClick: (ExpenseCategory) -> Unit = {
        selectedCategory = it
        showBottomSheet = true
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.Center),
                    contentColor = MaterialTheme.colorScheme.primary,
                    tabs = {
                        for (index in 0..1) {
                            Tab(
                                selected = index == selectedTabIndex.value,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                content = {
                                    Text(
                                        text = if (index == 0) "Outcome" else "Income",
                                        color = if (index == selectedTabIndex.value) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.outline
                                        }
                                    )
                                },
                            )
                        }
                    }
                )

                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier.align(alignment = Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Calendar"
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (selectedTabIndex.value == 0) {
                    OutcomeSelectionView(onCategoryClick)
                } else {
                    IncomeSelectionView(onCategoryClick)
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                CategoryBottomSheet(
                    expenseCategory = selectedCategory,
                    dismiss = {
                        showBottomSheet = false
                        navigateBack()
                    }
                )
            }
        }
    }


}

@Preview
@Composable
fun CategoryMainPagePreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CategoryMainPage(navigateBack = {})
        }
    }
}