package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

    Text(
        modifier = Modifier.padding(start = 15.dp, top = 10.dp),
        text = "User details",
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
        userData?.let { user ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Gender:", fontWeight = FontWeight.Bold
                    )
                    ExposedDropdownMenuBox(
                        modifier = Modifier.weight(1f),
                        expanded = isExpanded,
                        onExpandedChange = { isExpanded = it }
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = profileState.gender.toString(),
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        )
                        ExposedDropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Male",
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                onClick = {
                                    onGenderChange(Gender.MALE)
                                    isExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Female",
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                onClick = {
                                    onGenderChange(Gender.FEMALE)
                                    //viewModel.onEvent(ProfileEvent.UpdateGender(Gender.FEMALE))
                                    isExpanded = false
                                }
                            )
                        }
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.background)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { onDialogChange(DialogType.Age) }
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Age:", fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = profileState.age.toString(), fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.background)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { onDialogChange(DialogType.Height) }
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Height:", fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${profileState.height} cm", fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.background)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { onDialogChange(DialogType.Weight) }
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Weight:", fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${profileState.weight} kg", fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}