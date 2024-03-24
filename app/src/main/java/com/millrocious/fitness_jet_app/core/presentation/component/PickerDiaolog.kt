package com.millrocious.fitness_jet_app.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    //if (showDialog) {
    BasicAlertDialog(
        onDismissRequest = onCancel,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Surface(
            modifier = Modifier
                //.background(color = MaterialTheme.colorScheme.surfaceVariant)
                .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title)
                //TimePicker(state = timeState)
                content()
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancel) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = onConfirm) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
    //}
}