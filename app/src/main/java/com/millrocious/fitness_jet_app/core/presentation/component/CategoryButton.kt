package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryButton(
    modifier: Modifier = Modifier,
    iconShape: RoundedCornerShape,
    iconBackgroundColor: Color,
    iconTintColor: Color,
    title: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
        ) {
            IconBox(
                modifier = Modifier
                    .clip(iconShape)
                    .background(iconBackgroundColor), //MaterialTheme.colorScheme.errorContainer),
                tintColor = iconTintColor //MaterialTheme.colorScheme.onErrorContainer
            )
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}