package com.millrocious.fitness_jet_app.core.presentation.component.card_with_statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardStatisticsItemContainer(
    modifier: Modifier = Modifier,
//    title: String,
//    containerColor: Color,
//    textColor: Color,
    content: @Composable (Modifier) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content(Modifier.fillMaxHeight().weight(1f))
//        CardStatisticsItem(
//            title = title,
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxHeight(),
//            containerColor = containerColor.copy(alpha = 0.8f),
//            textColor = textColor,
//        ) {
//            content()
//        }
    }
}