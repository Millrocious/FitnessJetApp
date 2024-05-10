package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.bmi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.feature_user.presentation.util.BMIUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMISlider(
    modifier: Modifier = Modifier,
    bmi: Double,
    enabled: Boolean
) {
    Box(
        modifier = modifier.padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        val totalBMIRange = 10f

        val slices = listOf(
            BMIUtil.Slice(value = 3.5f, color = Color.Gray, text = "18.5"),
            BMIUtil.Slice(value = 6.5f, color = Color(0xFFCAE066), text = "25"),
            BMIUtil.Slice(value = 5.0f, color = Color(0xFFE08F66), text = "30"),
            BMIUtil.Slice(value = 10.0f, color = Color(0xFFE06666), text = "")
        )

        val position = BMIUtil.calculateSliderPosition(bmi, 15.0, 40.0, BMIUtil.getBMIShift(bmi))

        StackedBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            slices = slices
        )

        Slider(
            value = position.toFloat(),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            enabled = enabled,
            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
                disabledThumbColor = MaterialTheme.colorScheme.outline,
                disabledActiveTrackColor = Color.Transparent,
                disabledActiveTickColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent
            ),
            thumb = {
                val shape = RoundedCornerShape(5.dp)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .height(20.dp)
                        .border(1.dp, MaterialTheme.colorScheme.background, shape)
                        .background(MaterialTheme.colorScheme.primary, shape)
                )
            },
        )
    }
}