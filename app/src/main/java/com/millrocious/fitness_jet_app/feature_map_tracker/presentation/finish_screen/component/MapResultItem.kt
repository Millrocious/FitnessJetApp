package com.millrocious.fitness_jet_app.feature_map_tracker.presentation.finish_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MapResultItem(
    modifier: Modifier = Modifier,
    value: String,
    title: String,
    metric: String,
    icon: ImageVector,
) {
    ItemBody(
        modifier = modifier,
        title = title,
    ) {
        Icon(imageVector = icon, contentDescription = "")
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            modifier = Modifier
                .padding(start = 5.dp),
            text = " $metric",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}