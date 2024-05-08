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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
