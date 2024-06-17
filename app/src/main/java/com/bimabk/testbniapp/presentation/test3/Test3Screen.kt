package com.bimabk.testbniapp.presentation.test3

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bimabk.testbniapp.component.DonutChart
import com.bimabk.testbniapp.component.DonutChartDataCollection
import com.bimabk.testbniapp.core.model.DonutChartData
import com.bimabk.testbniapp.core.model.PortoUiModel
import com.bimabk.testbniapp.presentation.test2.ErrorSection
import com.bimabk.testbniapp.presentation.test2.LoadingSection
import com.bimabk.testbniapp.presentation.ui.theme.PetroleumGray
import com.bimabk.testbniapp.presentation.ui.theme.TestBNiAppTheme
import com.bimabk.testbniapp.utils.Resource
import com.bimabk.testbniapp.utils.formatPercentage

@Preview(showBackground = true)
@Composable
fun Test3Screen(
    viewModel: Test3ViewModel = hiltViewModel(),
) {
    TestBNiAppTheme {
        Column(
            modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 44.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.porto.collectAsState().value.let { resp ->
                when (resp) {
                    is Resource.Loading -> {
                        LoadingSection()
                    }

                    is Resource.Success -> {
                        Column(
                            modifier = Modifier.padding(top = 30.dp),
                        ) {
                            DonutChartSection(data = resp.data.donut)
                            Spacer(modifier = Modifier.size(16.dp))
                            LineChartSection(data = resp.data.line)
                        }
                    }

                    is Resource.Error -> {
                        ErrorSection(
                            tryAgain = { }
                        )
                    }

                    else -> {
                        LoadingSection()
                    }
                }
            }
        }
    }
}

@Composable
fun DonutChartSection(
    modifier: Modifier = Modifier,
    data: List<DonutChartData>
) {
    DonutChart(
        data = DonutChartDataCollection(data),
        listView = { selected ->
            AnimatedContent(targetState = selected, label = "DpAnimation") {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp)
                    ) {
                        items(it.listData) { value ->
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(vertical = 4.dp)
                                    .background(PetroleumGray, RoundedCornerShape(16.dp)),
                            ) {
                                Box(modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
                                    Text(
                                        text = value.trxDate,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = modifier
                                            .align(Alignment.CenterStart)
                                    )
                                    Text(
                                        text = value.nominal,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = modifier
                                            .align(Alignment.CenterEnd)
                                    )
                                    HorizontalDivider(
                                        modifier = modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(top = 6.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        selectionView = { selected ->
            AnimatedContent(targetState = selected, label = "DpAnimation") {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.amount.formatPercentage(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    )
}

@Composable
fun LineChartSection(
    modifier: Modifier = Modifier,
    data: PortoUiModel.LineChartData
) {

}