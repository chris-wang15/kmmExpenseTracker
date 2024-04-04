package com.tools.records.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tools.records.android.presentation.MainViewModel
import com.tools.records.android.presentation.category.CategoryMainPage
import com.tools.records.android.presentation.expense.ExpenseMainPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel: MainViewModel = viewModel(key = MAIN_VM_KEY)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = EXPENSE_SCREEN_ROUTE
                    ) {
                        composable(EXPENSE_SCREEN_ROUTE) {
                            ExpenseMainPage(
                                allExpense = viewModel.allExpense,
                                navigateToCategory = {
                                    navController.navigate(CATEGORY_SCREEN_ROUTE)
                                },
                            )
                        }
                        composable(CATEGORY_SCREEN_ROUTE) {
                            CategoryMainPage(
                                navigateBack = {
                                    navController.popBackStack()
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val MAIN_VM_KEY = "MainActivityVM"
        private const val EXPENSE_SCREEN_ROUTE = "ExpenseMainPage"
        private const val CATEGORY_SCREEN_ROUTE = "CategoryMainPage"
    }
}
