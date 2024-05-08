package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.ProfileState

@Composable
fun GoalsSection(
    profileState: ProfileState,
    onDialogChange: (DialogType) -> Unit,
) {
    Text(
        modifier = Modifier.padding(start = 15.dp, top = 10.dp),
        text = "Goals",
        fontWeight = FontWeight.Bold
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable { onDialogChange(DialogType.StepsGoal) }
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Steps by day:", fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "${profileState.stepsGoal}", fontWeight = FontWeight.Bold
                )
            }
        }
    }
}