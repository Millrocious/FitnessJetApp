package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IconBox(
    modifier: Modifier = Modifier,
    tintColor: Color,
) {
    Column(
        modifier = Modifier
            //.fillMaxHeight()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = modifier
        ) {
            Icon(
                modifier = Modifier.padding(5.dp),
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = tintColor//Color(0xFFB45D5D)
            )
        }
    }
}