package com.bimabk.testbniapp.core.model

import androidx.compose.ui.graphics.Color

data class PortoUiModel(
    val donut : List<DonutChartData>,
    val line: LineChartData
) {
    data class LineChartData(
        val month: List<Int>
    )
}


data class DonutChartData(
    val amount: Float,
    val color: Color,
    val title: String,
    val listData: List<TransactionItem>
) {
    data class TransactionItem(
        val trxDate: String,
        val nominal: String
    )
}