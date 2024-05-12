package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.runtime.Composable
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType
import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender

@Composable
fun <T> ProfileDialog(
    dialogType: DialogType,
    initialValue: T,
    onDismissRequest: () -> Unit,
    onConfirmation: (T) -> Unit
) {
    when (dialogType) {
        DialogType.Weight -> {
            DialogWheelSelector(
                title = "Weight",
                metric = "kg",
                twoWheels = true,
                initialValue = initialValue as Number,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedWeight ->
                    onConfirmation(selectedWeight.toFloat() as T)
                }
            )
        }

        DialogType.Height -> {
            DialogWheelSelector(
                title = "Height",
                metric = "cm",
                initialValue = initialValue as Number,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedHeight ->
                    onConfirmation(selectedHeight.toInt() as T)
                }
            )
        }

        DialogType.Age -> {
            DialogWheelSelector(
                title = "Age",
                metric = "",
                initialValue = initialValue as Number,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedAge ->
                    onConfirmation(selectedAge.toInt() as T)
                }
            )
        }

        DialogType.StepsGoal -> {
            DialogButtonSelector(
                title = "Steps goal",
                metric = "steps",
                initialValue = (initialValue as Number).toInt(),
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedSteps ->
                    onConfirmation(selectedSteps as T)
                }
            )
        }

        DialogType.CaloriesGoal -> {
            DialogButtonSelector(
                title = "Daily calories",
                metric = "kcal",
                initialValue = (initialValue as Number).toInt(),
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedCalories ->
                    onConfirmation(selectedCalories as T)
                }
            )
        }

        DialogType.Gender -> {
            DialogExposedMenuSelector(
                title = "Gender",
                metric = "",
                initialValue = initialValue as Gender,
                options = listOf(Gender.MALE, Gender.FEMALE),
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedGender ->
                    onConfirmation(selectedGender as T)
                }
            )
        }
    }
}