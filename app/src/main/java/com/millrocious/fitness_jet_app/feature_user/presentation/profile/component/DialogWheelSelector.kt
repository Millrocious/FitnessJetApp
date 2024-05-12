package com.millrocious.fitness_jet_app.feature_user.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.millrocious.fitness_jet_app.core.presentation.component.wheel_selector.ScrollableVerticalSelector
import kotlin.math.roundToInt


@Composable
fun DialogWheelSelector(
    title: String,
    metric: String,
    twoWheels: Boolean = false,
    initialValue: Number = 0,
    onDismissRequest: () -> Unit,
    onConfirmation: (Number) -> Unit
) {
    var wholePart by remember {
        mutableIntStateOf(
            when (initialValue) {
                is Int -> initialValue.toInt()
                is Float -> initialValue.toInt()
                else -> 0
            }
        )
    }

    var fractionalPart by remember {
        mutableIntStateOf(
            when (initialValue) {
                is Float -> ((initialValue.toFloat() - initialValue.toInt()) * 10).roundToInt()
                else -> 0
            }
        )
    }


    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    text = title
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ScrollableVerticalSelector(
                        modifier = Modifier.width(50.dp),
                        startIndex = wholePart,
                        onItemSelected = {
                            wholePart = it
                        }
                    )
                    if (twoWheels) {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = "."
                        )
                        ScrollableVerticalSelector(
                            modifier = Modifier.width(50.dp),
                            startIndex = fractionalPart,
                            count = 10,
                            onItemSelected = {
                                fractionalPart = it
                            }
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = metric
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            if (twoWheels)
                                onConfirmation(wholePart.toFloat() + fractionalPart.toFloat() / 10f)
                            else
                                onConfirmation(wholePart)
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}


@Composable
fun DialogButtonSelector(
    title: String,
    metric: String,
    initialValue: Int = 0,
    onDismissRequest: () -> Unit,
    onConfirmation: (Int) -> Unit
) {
    var wholePart by remember { mutableIntStateOf(initialValue) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    text = title
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = wholePart.toString()
                        )
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = metric
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { if (wholePart > 99) wholePart -= 100 },
//                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text("-100")
                        }
                        Button(
                            onClick = { wholePart += 100 },
//                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text("+100")
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            onConfirmation(wholePart)
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun <T> DialogExposedMenuSelector(
    title: String,
    metric: String,
    initialValue: T,
    options: List<T>,
    onDismissRequest: () -> Unit,
    onConfirmation: (T) -> Unit
) {
    var wholePart by remember { mutableStateOf(initialValue) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    text = title
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        SimpleDropdownMenu(
                            options = options,
                            selectedOption = wholePart
                        ) { newValue ->
                            wholePart = newValue
                        }

                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = metric
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            onConfirmation(wholePart)
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SimpleDropdownMenu(
    modifier: Modifier = Modifier,
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOption.toString(),
            onValueChange = {},
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(fontSize = 12.sp, text =  option.toString()) },
                    onClick = {
                        onOptionSelected(option)
                        isExpanded = false
                    }
                )
            }
        }
    }
}