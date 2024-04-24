package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionsSection() {
    Text(
        modifier = Modifier.padding(top = 10.dp, start = 15.dp),
        text = "Actions",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                MaterialTheme.colorScheme.errorContainer,
                RoundedCornerShape(20.dp)
            )
    ) {
        ActionItem(
            icon = Icons.Default.Delete,
            color = Color(0xFF222222),
            text = "Remove"
        )
    }
}