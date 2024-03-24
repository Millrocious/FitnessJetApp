package com.millrocious.fitness_jet_app.core.presentation.component.card_with_statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressureStatistics
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRateStatistics

@Composable
fun CardWithStatistics(
    title: String,
    contentColor: Color,
    containerColor: Color,
    itemContainerColor: Color,
    dateContentColor: Color,
    dateItemContainerColor: Color,
    averageContent: @Composable () -> Unit,
    lastRecordContent: @Composable () -> Unit,
    minContent: @Composable () -> Unit,
    maxContent: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.padding(15.dp),
        colors = CardDefaults.cardColors(containerColor),
        shape = RoundedCornerShape(25.dp)
    ) {
        Column {
            CardHeader(title = title, contentColor = contentColor)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top
            ) {
                CardStatisticsItemContainer { modifier ->
                    CardStatisticsItem(
                        modifier = modifier,
                        title = "Average",
                        containerColor = itemContainerColor,
                        textColor = contentColor
                    ) {
                        averageContent()
                    }
                    CardStatisticsItem(
                        modifier = modifier,
                        title = "Last Record",
                        containerColor = dateItemContainerColor,
                        textColor = dateContentColor
                    ) {
                        lastRecordContent()
                    }
                }
                CardStatisticsItemContainer { modifier ->
                    CardStatisticsItem(
                        modifier = modifier,
                        title = "Min",
                        containerColor = itemContainerColor,
                        textColor = contentColor
                    ) {
                        minContent()
                    }
                    CardStatisticsItem(
                        modifier = modifier,
                        title = "Max",
                        containerColor = itemContainerColor,
                        textColor = contentColor
                    ) {
                        maxContent()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HeartRateCardPreview() {
    val statistics = BloodPressureStatistics(
        10.456,
        0,
        0,
        0.0,
        0,
        0
    )

    val heartRateStatistics = HeartRateStatistics(
        0.0,
        0,
        0
    )

    CardWithStatistics(
        title = "Statistics Card",
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer,
        itemContainerColor = MaterialTheme.colorScheme.surface,
        dateContentColor = MaterialTheme.colorScheme.primary,
        dateItemContainerColor = MaterialTheme.colorScheme.primaryContainer,
        averageContent = {
            Text(text = "systolic:     10")
            Text(text = "diastolic:    10")
        },
        lastRecordContent = { Text(text = "Last Record Value") },
        minContent = { Text(text = "Min Value") },
        maxContent = { Text(text = "Max Value") }
    )
}
