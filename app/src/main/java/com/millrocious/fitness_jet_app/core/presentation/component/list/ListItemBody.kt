package com.millrocious.fitness_jet_app.core.presentation.component.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ListItemBody(
    modifier: Modifier = Modifier,
    timestamp: OffsetDateTime,
    iconBox: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            iconBox()
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.outline,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.sp,
                    text = timestamp.format(DateTimeFormatter.ofPattern("hh:mm a"))
                )
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    content()
                }
            }
        }
    }
}