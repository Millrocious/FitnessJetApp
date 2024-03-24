package com.millrocious.fitness_jet_app.core.presentation.component.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ListItemWithActions(
    expandableRow: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    var isExpandedRowVisible by remember {
        mutableStateOf(false)
    }

    val animatedBackgroundColor = animateColorAsState(
        targetValue = if (isExpandedRowVisible)
            MaterialTheme.colorScheme.surface
        else
            MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(400, 0, LinearEasing),
        label = "",
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(animatedBackgroundColor.value)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isExpandedRowVisible = !isExpandedRowVisible
            },
    ) {
        content()
        expandableRow(isExpandedRowVisible)
    }
}