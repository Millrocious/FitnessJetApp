package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableRow(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    animationDuration: Int = 300,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut(),
        enter = expandVertically(
            animationSpec = tween(durationMillis = animationDuration),
            expandFrom = Alignment.Top
        ) + fadeIn()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content()
        }
    }
}