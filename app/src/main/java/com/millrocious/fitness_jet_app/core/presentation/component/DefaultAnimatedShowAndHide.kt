package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun DefaultAnimatedShowAndHide(
    showContent: Boolean,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = showContent,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 300)
        ) + fadeOut(),
    ) {
        content()
    }
}