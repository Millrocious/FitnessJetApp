package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.runtime.Composable
import com.millrocious.fitness_jet_app.feature_user.domain.model.DialogType

@Composable
fun ProfileDialog(
    dialogType: DialogType,
    initialValue: Number,
    onDismissRequest: () -> Unit,
    onConfirmation: (Number) -> Unit
) {
    when (dialogType) {
        DialogType.Weight -> {
            DialogWheelSelector(
                title = "Weight",
                metric = "kg",
                twoWheels = true,
                initialValue = initialValue,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedWeight ->
                    onConfirmation(selectedWeight.toFloat())
                }
            )
        }

        DialogType.Height -> {
            DialogWheelSelector(
                title = "Height",
                metric = "cm",
                initialValue = initialValue,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedHeight ->
                    onConfirmation(selectedHeight.toInt())
                }
            )
        }

        DialogType.Age -> {
            DialogWheelSelector(
                title = "Age",
                metric = "",
                initialValue = initialValue,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedAge ->
                    onConfirmation(selectedAge.toInt())
                }
            )
        }

        DialogType.StepsGoal -> {
            DialogButtonSelector(
                title = "Steps goal",
                metric = "steps",
                initialValue = initialValue.toInt(),
                onDismissRequest = { onDismissRequest() },
                onConfirmation = { selectedSteps ->
                    onConfirmation(selectedSteps)
                }
            )
        }
    }
}