package com.millrocious.fitness_jet_app.core.presentation.component.card_with_statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardHeader(title: String, contentColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(15.dp),
            fontSize = 30.sp,
            color = contentColor,
            fontWeight = FontWeight.Bold
        )
        Icon(
            modifier = Modifier
                .padding(15.dp)
                .size(32.dp),
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Heart",
            tint = contentColor,
        )
    }
}