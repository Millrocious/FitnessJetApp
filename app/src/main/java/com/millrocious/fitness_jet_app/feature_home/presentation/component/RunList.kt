package com.millrocious.fitness_jet_app.feature_home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.DoNotStep
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.core.presentation.util.MetricsUtil
import com.millrocious.fitness_jet_app.feature_home.presentation.HomeState
import com.millrocious.fitness_jet_app.feature_map_tracker.data.model.Run

@Composable
fun RunList(
    modifier: Modifier = Modifier,
    state: HomeState,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            TotalStepsCard(totalSteps = state.totalSteps)
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "All records",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        items(state.runList) { run ->
            RunItem(run = run)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun RunItem(
    run: Run,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            bitmap = run.img.asImageBitmap(),
            contentDescription = "Run Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(15.dp)
                        )
                        .border(
                            2.dp, MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(15.dp)
                        )
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.AutoMirrored.Rounded.DirectionsRun,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Column {
                    Text(
                        text = "Total running time",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = MetricsUtil.formatTime(run.durationInMillis),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = MetricsUtil.formatTimestamp(run, MetricsUtil.TimestampFormat.MonthDayYearWithTime),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Distance",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.LocationOn,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = "${run.distanceInMeters / 1000.0} km",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Steps",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.DoNotStep,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = run.steps.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Calories",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.LocalFireDepartment,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = "0 kcal",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                }
            }
        }
    }
//    Column(
//        modifier = modifier
//            .fillMaxWidth(),
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(10.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = "${run.timestamp.dayOfMonth} ${run.timestamp.month} ${run.timestamp.year}",
//                    style = MaterialTheme.typography.labelSmall,
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Row {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.DirectionsWalk,
//                        contentDescription = "",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Text(
//                        text = "Walk",
//                        style = MaterialTheme.typography.bodyLarge,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//                }
//                Spacer(modifier = Modifier.height(4.dp))
//                Row(
//                    modifier = Modifier.height(IntrinsicSize.Min),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.DoNotStep,
//                        contentDescription = "",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                    Text(
//                        text = run.steps.toString(),
//                    )
//                    Icon(
//                        imageVector = Icons.Default.LocationOn,
//                        contentDescription = "",
//                        tint = MaterialTheme.colorScheme.secondary
//                    )
//                    Text(
//                        text = "${run.distanceInMeters / 1000.0} km",
//                    )
//                }
//            }
//            Image(
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(RoundedCornerShape(10.dp)),
//                bitmap = run.img.asImageBitmap(),
//                contentDescription = "Run Image",
//            )
//        }
//    }
}

@Composable
fun RunItem2(
    modifier: Modifier = Modifier,
    timestamp: String,
    distanceInMeters: String,
    steps: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.outline)
        )
        Column(
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(15.dp)
                        )
                        .border(
                            2.dp, MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(15.dp)
                        )
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.AutoMirrored.Rounded.DirectionsRun,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Column {
                    Text(
                        text = "Total running time",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "00:00:00",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = timestamp,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Distance",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.LocationOn,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = distanceInMeters,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Steps",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.DoNotStep,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = steps,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Text(
                                text = "Calories",
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Rounded.LocalFireDepartment,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.outline
                                )
                                Text(
                                    text = "104 kcal",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun RunListPrev() {
    RunItem2(
        timestamp = "Apr 12, 2024 - 12:15 PM",
        distanceInMeters = "0.34 km",
        steps = "2000",
    )
}