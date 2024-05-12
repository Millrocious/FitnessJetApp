package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Height
import androidx.compose.material.icons.rounded.PeopleOutline
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender
import com.millrocious.fitness_jet_app.feature_user.presentation.profile.ProfileState
import com.millrocious.fitness_jet_app.feature_user.presentation.sign_in.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsSection(
    userData: UserData?,
    profileState: ProfileState,
    onGenderChange: (Gender) -> Unit,
    onDialogChange: (DialogType) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
            text = "User details",
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            SectionCard(
                title = "Gender",
                icon = Icons.Rounded.PeopleOutline,
                onClick = { onDialogChange(DialogType.Gender) },
                iconColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = profileState.gender.toString(),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            SectionCard(
                title = "Age",
                icon = Icons.Rounded.Cake,
                iconColor = MaterialTheme.colorScheme.primary,
                onClick = { onDialogChange(DialogType.Age) }
            ) {
                Text(
                    text = profileState.age.toString(),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            SectionCard(
                title = "Height",
                icon = Icons.Rounded.Height,
                iconColor = MaterialTheme.colorScheme.primary,
                onClick = { onDialogChange(DialogType.Height) }
            ) {
                Text(
                    text = "${profileState.height} cm",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            SectionCard(
                title = "Weight",
                icon = Icons.Rounded.Straighten,
                iconColor = MaterialTheme.colorScheme.primary,
                onClick = { onDialogChange(DialogType.Weight) }
            ) {
                Text(
                    text = "${profileState.weight} kg",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}