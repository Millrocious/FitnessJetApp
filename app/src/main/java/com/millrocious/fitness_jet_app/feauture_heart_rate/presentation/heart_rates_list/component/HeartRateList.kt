package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.millrocious.fitness_jet_app.core.presentation.component.list.DateHeader
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.core.presentation.component.ActionButton
import com.millrocious.fitness_jet_app.core.presentation.component.ExpandableRow
import com.millrocious.fitness_jet_app.core.presentation.component.list.ListItemWithActions
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import java.time.LocalDate
import java.time.OffsetDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeartRateList(
    state: Map<LocalDate, List<HeartRate>>,
    onNavigate: (String) -> Unit,
    onDelete: (HeartRate) -> Unit
) {
    val lazyColumnState = rememberLazyListState()

    LazyColumn(
        state = lazyColumnState,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        state.forEach { (date, items) ->
            stickyHeader {
                DateHeader(text = date.toString())
            }

            itemsIndexed(
                items,
                key = { _, item -> item.uuid }
            ) { _, heartRate ->

                ListItemWithActions(
                    expandableRow = { isExpandedRowVisible ->
                        ExpandableRow(isVisible = isExpandedRowVisible) {
                            ActionButton(
                                onClick = {
                                    onNavigate(Screen.AddEditHeartRateScreen.route +
                                            "?heartRateId=${heartRate.id}"
                                    )
                                },
                                actionIcon = Icons.Default.Edit,
                                actionTitle = "Edit",
                            )
                            ActionButton(
                                onClick = { onDelete(heartRate) },
                                actionIcon = Icons.Default.Delete,
                                actionTitle = "Delete",
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                ) {
                    HeartRateItem(
                        heartRate = heartRate,
                    )
                }
            }
        }
    }
}


@Preview(apiLevel = 33, name = "Light", showBackground = true)
@Preview(
    apiLevel = 33,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun HeartRateListPreview() {
    val heartRateMap: MutableMap<LocalDate, List<HeartRate>> = mutableMapOf()

    // Adding heart rates for a specific date
    val date1 = LocalDate.of(2024, 2, 17)
    val heartRates1 =
        listOf(
            HeartRate(
                heartBeats = 75,
                selectedTimestamp = OffsetDateTime.now()
            ),
            HeartRate(
                heartBeats = 80,
                selectedTimestamp = OffsetDateTime.now()
            ),
            HeartRate(
                heartBeats = 72,
                selectedTimestamp = OffsetDateTime.now()
            )
        )
    heartRateMap[date1] = heartRates1

    // Adding heart rates for another date
    val date2 = LocalDate.of(2024, 2, 18)
    val heartRates2 =
        listOf(
            HeartRate(
                heartBeats = 78,
                selectedTimestamp = OffsetDateTime.now()
            ),
            HeartRate(
                heartBeats = 82,
                selectedTimestamp = OffsetDateTime.now()
            ),
            HeartRate(
                heartBeats = 76,
                selectedTimestamp = OffsetDateTime.now()
            )
        )
    heartRateMap[date2] = heartRates2

    SportAndHealthManagerTheme {
        Surface {
            HeartRateList(
                state = heartRateMap,
                onNavigate = {}
            ) {}
        }
    }
}