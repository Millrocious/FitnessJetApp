package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    onClick: () -> Unit,
    actionIcon: ImageVector,
    actionTitle: String,
    contentColor: Color? = null
) {
    TextButton(onClick = {
        onClick()
    }) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if (contentColor != null) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = "",
                    tint = contentColor
                )
                Text(text = actionTitle, color = contentColor)
            } else {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = ""
                )
                Text(text = actionTitle)
            }
        }
    }
}