package com.bimabk.testbniapp.utils

import com.bimabk.testbniapp.core.model.DonutChartData
import com.bimabk.testbniapp.core.model.PortoUiResponse
import com.bimabk.testbniapp.core.model.PortoUiModel
import com.bimabk.testbniapp.presentation.ui.theme.VividOrange

fun toPortoUiModel(data: List<PortoUiResponse>): PortoUiModel {
    return PortoUiModel(
        donut = data.flatMap { portoUiModel ->
            portoUiModel.data?.mapNotNull { dataItem ->
                dataItem?.let { porto ->
                    DonutChartData(
                        amount = porto.percentage?.toFloatOrNull()?.times(10) ?: 0f,
                        color = porto.label?.formatColor() ?: VividOrange,
                        title = porto.label.orEmpty(),
                        listData = porto.data?.mapNotNull { trx ->
                            DonutChartData.TransactionItem(
                                trxDate = trx?.trxDate.orEmpty().formatDate(),
                                nominal = trx?.nominal?.formatRupiah()?.replace(",00", "").orEmpty()
                            )
                        }.orEmpty()
                    )
                }
            } ?: emptyList()
        },
        line = PortoUiModel.LineChartData(month = data.flatMap {
            it.lineData?.month?.filterNotNull() ?: emptyList()
        })
    )
}