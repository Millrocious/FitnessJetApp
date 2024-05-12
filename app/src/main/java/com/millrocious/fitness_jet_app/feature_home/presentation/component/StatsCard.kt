package com.millrocious.fitness_jet_app.feature_home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.twotone.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_home.presentation.HomeState

@Composable
fun StatsCard(
    homeState: HomeState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StepInfo(
            title = "Steps Taken",
            icon = Icons.Default.Directions,
            iconTintColor = MaterialTheme.colorScheme.primary,
            value = homeState.totalSteps.toString(),
            goalValue = homeState.stepsGoal.toString(),
        )

        VerticalDivider(
            modifier = Modifier
                .height(50.dp)
        )

        StepInfo(
            title = "Calories burned",
            icon = Icons.TwoTone.LocalFireDepartment,
            iconTintColor = MaterialTheme.colorScheme.errorContainer,
            value = homeState.totalBurnedCalories.toString(),
            goalValue = homeState.burnedCaloriesGoal.toString()
        )
    }
}

@Composable
private fun StepInfo(
    title: String,
    icon: ImageVector,
    iconTintColor: Color,
    value: String,
    goalValue: String
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier.padding(top = 5.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "/$goalValue",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline,
                fontSize = 10.sp,
            )

        }
    }
}

@Preview
@Composable
private fun StatsCardPrev() {
    StatsCard(
        HomeState()
    )
}