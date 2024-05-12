package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.bmi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.ProfileState
import com.millrocious.fitness_jet_app.feature_user.presentation.util.BMIUtil

@Composable
fun BMISection(
    profileState: ProfileState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (profileState.height != 0 && profileState.weight != 0f) {
                val bmi = BMIUtil.calculateBMI(
                    profileState.weight.toDouble(),
                    (profileState.height.toFloat() / 100.0)
                )

                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Your BMI", fontWeight = FontWeight.Bold
                )
                Text(
                    text = "%.1f".format(bmi), fontWeight = FontWeight.Bold,
                    color = BMIUtil.getColorForBMI(bmi),
                    fontSize = 28.sp
                )
                BMISlider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    bmi = bmi,
                    enabled = false
                )
            }
        }
    }
}