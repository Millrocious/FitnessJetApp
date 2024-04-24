package com.millrocious.fitness_jet_app.feature_home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
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
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                bitmap = run.img.asImageBitmap(),
                contentDescription = "Run Image",
            )
            Column {
                Text(
                    text = run.timestamp.toString(),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = run.distanceInMeters.toString(),
                )
            }

        }
    }
}

//@Preview
//@Composable
//private fun RunListPrev() {
//    RunList()
//}