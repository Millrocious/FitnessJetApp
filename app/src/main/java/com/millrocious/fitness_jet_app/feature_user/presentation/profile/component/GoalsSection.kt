package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DoNotStep
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.ProfileState

@Composable
fun GoalsSection(
    profileState: ProfileState,
    onDialogChange: (DialogType) -> Unit,
) {

    Column {
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
            text = "Goals",
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            SectionCard(
                title = "Daily steps",
                icon = Icons.Rounded.DoNotStep,
                onClick = { onDialogChange(DialogType.StepsGoal) },
                iconColor = MaterialTheme.colorScheme.tertiary
            ) {
                Text(
                    text = profileState.stepsGoal.toString(),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            SectionCard(
                title = "Daily calories",
                icon = Icons.Rounded.LocalFireDepartment,
                iconColor = MaterialTheme.colorScheme.errorContainer,
                onClick = { onDialogChange(DialogType.CaloriesGoal) }
            ) {
                Text(
                    text = profileState.burnedCaloriesGoal.toString(),
                    color = MaterialTheme.colorScheme.errorContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoalsSectionPrev() {
    GoalsSection(profileState = ProfileState(), onDialogChange = {})
}