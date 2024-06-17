package com.bimabk.testbniapp.utils

import androidx.compose.ui.graphics.Color
import com.bimabk.testbniapp.presentation.ui.theme.Green
import com.bimabk.testbniapp.presentation.ui.theme.MetallicYellow
import com.bimabk.testbniapp.presentation.ui.theme.Sapphire
import com.bimabk.testbniapp.presentation.ui.theme.VividOrange
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatColor(): Color {
    return when {
        this.equals("Tarik Tunai", true) -> {
            VividOrange
        }

        this.equals("QRIS Payment", true) -> {
            Sapphire
        }

        this.equals("Topup Gopay", true) -> {
            Green
        }

        this.equals("Lainnya", true) -> {
            MetallicYellow
        }

        else -> {
            VividOrange
        }
    }
}

fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
        val date = inputFormat.parse(this) ?: return ""
        val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        return outputFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun Int.formatRupiah(): String {
    return try {
        val locale = Locale.getDefault()
        val formatter = NumberFormat.getCurrencyInstance(locale)
        formatter.format(this)
    } catch (e: Exception) {
        ""
    }
}

fun Float.formatPercentage(): String {
    return String.format("%.2f%%", this / 10)
        .replace(",00%", "%")
        .replace("0%", "%")
}