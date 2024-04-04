package com.tools.records.domain.category

class CategoryDataSource {
    private val categoryList: List<ExpenseCategory> = listOf(
        // Daily
        ExpenseCategory(1, "Maintenance"),
        ExpenseCategory(2, "Social"),
        ExpenseCategory(3, "Phone"),
        ExpenseCategory(4, "Medical"),
        ExpenseCategory(5, "Traffic"),
        ExpenseCategory(6, "Groceries"),
        ExpenseCategory(7, "Shopping"),
        ExpenseCategory(8, "Dining"),
        // Activity
        ExpenseCategory(9, "Office"),
        ExpenseCategory(10, "Education"),
        ExpenseCategory(11, "Books"),
        ExpenseCategory(12, "Travel"),
        ExpenseCategory(13, "Fun"),
        ExpenseCategory(14, "Sports"),
        // Family
        ExpenseCategory(15, "Pet"),
        ExpenseCategory(16, "Parents"),
        ExpenseCategory(17, "Child"),
        // Other
        ExpenseCategory(18, "Other"),
        ExpenseCategory(19, "Tax"),
        // Income
        ExpenseCategory(20, "Other"),
        ExpenseCategory(21, "Borrow"),
        ExpenseCategory(22, "Stock"),
        ExpenseCategory(23, "Fund"),
        ExpenseCategory(24, "Rent"),
        ExpenseCategory(25, "Invest"),
        ExpenseCategory(26, "Parttime"),
        ExpenseCategory(27, "Salary"),
    )

    val allDailyCategories = categoryList.subList(0, 7)

    val allActivityCategories = categoryList.subList(8, 13)

    val allFamilyCategories = categoryList.subList(14, 16)

    val allOtherCategories = categoryList.subList(17, 18)

    val allIncomeCategories = categoryList.subList(19, 27)

    fun isOutcome(id: Long?): Boolean {
        id ?: return true
        return id in 1..19
    }

    fun getCategoryById(id: Long): ExpenseCategory {
        return categoryList[id.toInt() - 1]
    }
}