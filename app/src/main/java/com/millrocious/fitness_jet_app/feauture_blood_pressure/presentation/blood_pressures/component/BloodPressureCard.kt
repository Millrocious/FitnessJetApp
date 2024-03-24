package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.core.presentation.component.card_with_statistics.CardWithStatistics
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressureStatistics

@Composable
fun BloodPressureCard(
    date: String,
    statistics: BloodPressureStatistics
) {
    CardWithStatistics(
        title = "Blood pressure",
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer,
        itemContainerColor = MaterialTheme.colorScheme.surface,
        dateContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        dateItemContainerColor = MaterialTheme.colorScheme.primaryContainer,
        averageContent = {
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "systolic: ${statistics.averageSystolic}"
            )
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "diastolic: ${statistics.averageDiastolic}"
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
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "systolic: ${statistics.minimumSystolic}"
            )
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "diastolic: ${statistics.minimumDiastolic}"
            )
        },
        maxContent = {
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "systolic: ${statistics.maximumSystolic}"
            )
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "diastolic: ${statistics.maximumDiastolic}"
            )
        }
    )
}