package com.tools.records.android.utils

import java.text.NumberFormat
import java.util.Locale

object AmountUtil {
    fun formatAmount(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        return formatter.format(amount)
    }

    fun roundAmount(value: Double): Double {
        return Math.round(value * 100) / 100.0
    }
}