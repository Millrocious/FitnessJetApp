package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.core.presentation.component.card_with_statistics.CardWithStatistics
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRateStatistics

@Composable
fun HeartRateCard(
    date: String,
    statistics: HeartRateStatistics
) {
    CardWithStatistics(
        title = "Heart Rate",
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer,
        itemContainerColor = MaterialTheme.colorScheme.surface,
        dateContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        dateItemContainerColor = MaterialTheme.colorScheme.primaryContainer,
        averageContent = {
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "${statistics.average} bpm"
            )
        },
        lastRecordContent = {
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                text = date
            )
        },
        minContent = {
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "${statistics.minimum} bpm"
            )
        },
        maxContent = {
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "${statistics.maximum} bpm"
            )
        }
    )
}