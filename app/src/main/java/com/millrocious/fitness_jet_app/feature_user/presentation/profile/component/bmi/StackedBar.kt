package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component.bmi

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.feature_user.presentation.util.BMIUtil

@Composable
fun StackedBar(modifier: Modifier, slices: List<BMIUtil.Slice>) {
    val textMeasurer = rememberTextMeasurer()

    val textColor = MaterialTheme.colorScheme.onBackground

    val textLayoutResults = remember {
        mutableListOf<TextLayoutResult>().apply {
            slices.forEach {
                val textLayoutResult: TextLayoutResult =
                    textMeasurer.measure(
                        text = AnnotatedString(it.text),
                        style = TextStyle(
                            color = textColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                add(textLayoutResult)
            }
        }
    }

    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val padding = 2.dp.toPx()

        var currentX = padding
        slices.forEachIndexed { index: Int, slice: BMIUtil.Slice ->
            val width = (slice.value) / 25f * (canvasWidth - (slices.size + 1) * padding)

            drawRect(
                color = slice.color,
                topLeft = Offset(currentX, 0f),
                size = Size(width, canvasHeight),
            )

            val textSize = textLayoutResults[index].size
            val style = textLayoutResults[index].layoutInput.style
            if (index < slices.lastIndex) {
                val nextX = currentX + width + padding
                val gapCenter = (currentX + width + nextX) / 2

                drawText(
                    textMeasurer = textMeasurer,
                    text = slice.text,
                    topLeft = Offset(
                        x = gapCenter - textSize.width / 2,
                        y = canvasHeight / 2 - (textSize.height * 1.5).toInt()
                    ),
                    style = style
                )
            }

            currentX += width + padding
        }
    }
}