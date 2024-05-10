package com.millrocious.fitness_jet_app.feature_user.presentation.util

import androidx.compose.ui.graphics.Color

object BMIUtil {
    fun getBMIShift(bmi: Double): Double {
        return when {
            bmi < 18.5 -> 0.0
            bmi < 25.0 -> 0.01
            bmi < 30.0 -> 0.025
            else -> 0.035
        }
    }

    fun calculateSliderPosition(bmi: Double, minBMI: Double, maxBMI: Double, shift: Double): Double {
        return (bmi - minBMI) / (maxBMI - minBMI) + shift
    }

    fun getColorForBMI(bmi: Double): Color {
        return when {
            bmi < 18.5 -> Color.Gray   // Underweight
            bmi < 25 -> Color(0xFFCAE066)    // Normal weight
            bmi < 30 -> Color(0xFFE08F66)    // Overweight
            else -> Color(0xFFE06666)          // Obese
        }
    }

    fun calculateBMI(weightInKg: Double, heightInMeters: Double): Double {
        return weightInKg / (heightInMeters * heightInMeters)
    }

    data class Slice(val value: Float, val color: Color, val text: String = "")
}