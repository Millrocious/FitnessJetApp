package com.millrocious.fitness_jet_app.core.presentation.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DateHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun DateHeaderPreview() {
    DateHeader(text = "19.01.2024")
}