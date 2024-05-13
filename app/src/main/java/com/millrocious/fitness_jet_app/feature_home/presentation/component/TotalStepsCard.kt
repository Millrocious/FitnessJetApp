package com.millrocious.fitness_jet_app.feature_home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TotalStepsCard(
    modifier: Modifier = Modifier,
    userStepsGoal: Int = 5000,
    userBurnedCaloriesGoal: Int = 500,
    totalBurnedCalories: Int? = 0,
    totalSteps: Long? = 0,
    profilePictureUrl: String?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .padding(16.dp),
            progress = { (totalSteps?.toFloat() ?: 0f) / userStepsGoal.toFloat() },
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 14.dp,
            trackColor = MaterialTheme.colorScheme.surface,
            strokeCap = StrokeCap.Round,
        )
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .padding(16.dp),
            progress = { (totalBurnedCalories?.toFloat() ?: 0f) / userBurnedCaloriesGoal.toFloat() },
            color = MaterialTheme.colorScheme.errorContainer,
            strokeWidth = 14.dp,
            trackColor = MaterialTheme.colorScheme.surface,
            strokeCap = StrokeCap.Round,
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (profilePictureUrl != null) {
                AsyncImage(
                    model = profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
private fun TotalStepsPrev() {
    TotalStepsCard(totalSteps = 3000, profilePictureUrl = "")
}