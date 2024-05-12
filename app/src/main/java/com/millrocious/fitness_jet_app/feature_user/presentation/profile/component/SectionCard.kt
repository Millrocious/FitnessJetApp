package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.SectionCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    iconColor: Color,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(top = 15.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.outline
            )
            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = icon,
                    tint = iconColor,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                content()
            }
        }
    }
}