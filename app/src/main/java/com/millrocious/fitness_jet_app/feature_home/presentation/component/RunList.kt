package com.millrocious.fitness_jet_app.feature_home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DoNotStep
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Spacer(modifier = Modifier.height(8.dp))
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
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${run.timestamp.dayOfMonth} ${run.timestamp.month} ${run.timestamp.year}" ,
                    style = MaterialTheme.typography.labelSmall,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.DirectionsWalk,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Walk",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.DoNotStep,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = run.steps.toString(),
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "${run.distanceInMeters / 1000.0} km",
                    )
                }
            }
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                bitmap = run.img.asImageBitmap(),
                contentDescription = "Run Image",
            )
//            Column {
//                Text(
//                    text = run.timestamp.toString(),
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = run.distanceInMeters.toString(),
//                )
//            }

        }
    }
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
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = timestamp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Walk",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = steps,
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = distanceInMeters,
                    )
                }

            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.background),
            )
        }
    }
}

@Preview
@Composable
private fun RunListPrev() {
    RunItem2(
        timestamp = "12 april 2024",
        distanceInMeters = "0.34 km",
        steps = "2000",
    )
}