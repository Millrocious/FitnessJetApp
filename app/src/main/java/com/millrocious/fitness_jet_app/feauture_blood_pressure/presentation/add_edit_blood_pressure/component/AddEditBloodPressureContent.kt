package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.core.domain.util.DateUtils
import com.millrocious.fitness_jet_app.core.presentation.component.PickerDialog
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBloodPressureContent(
    modifier: Modifier = Modifier,
    onSystolicChange: (Int) -> Unit,
    onDiastolicChange: (Int) -> Unit,
    bloodPressureDiastolicState: Int,
    bloodPressureSystolicState: Int,
    selectedTimestamp: OffsetDateTime,
    onTimestampChange: (OffsetDateTime) -> Unit
) {

    // Time data
    val selectedHour = selectedTimestamp.hour
    val selectedMinute = selectedTimestamp.minute

    var showTimeDialog by remember { mutableStateOf(false) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    // Date data
    val selectedDate = selectedTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    var showDateDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState(
        initialSelectedDateMillis = selectedTimestamp.toInstant().toEpochMilli()
    )


    if (showTimeDialog) {
        PickerDialog(
            onCancel = { showTimeDialog = false },
            onConfirm = {
                showTimeDialog = false
                onTimestampChange(
                    selectedTimestamp.withHour(timeState.hour).withMinute(timeState.minute)
                )
            }
        ) {
            TimePicker(state = timeState)
        }
    }

    if (showDateDialog) {
        PickerDialog(
            onCancel = { showDateDialog = false },
            onConfirm = {
                showDateDialog = false
                onTimestampChange(
                    dateState.selectedDateMillis?.let {
                        Instant.ofEpochMilli(it).atOffset(ZoneOffset.UTC)
                    } ?: OffsetDateTime.now()
                )
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = "Blood pressure systolic",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ScrollableHeartRateSelector(
                    startIndex = bloodPressureSystolicState,
                    onValueSelected = {
                        onSystolicChange(it)
                    }
                )
            }
        }


        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = "Blood pressure diastolic",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ScrollableHeartRateSelector(
                    startIndex = bloodPressureDiastolicState,
                    onValueSelected = {
                        onDiastolicChange(it)
                    }
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 40.dp, bottom = 15.dp),
            text = "Time and date",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        //Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(text = "Select", fontWeight = FontWeight.Bold)
                        Text(
                            text = "time",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    //Text(text = "Select time: ")
                }
                TextButton(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 100.dp),
                    onClick = { showTimeDialog = true }) {
                    Text(
                        text = "%02d:%02d".format(selectedHour, selectedMinute)
                    )
                }
            }
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.background)
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(text = "Select", fontWeight = FontWeight.Bold)
                        Text(
                            text = "date",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                TextButton(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 100.dp),
                    onClick = { showDateDialog = true }) {
                    Text(
                        text = if (selectedDate == "") DateUtils().dateToString(
                            DateUtils().convertMillisToLocalDate(
                                System.currentTimeMillis()
                            )
                        ) else selectedDate
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 33)
@Preview(apiLevel = 33, name = "Light", showBackground = true)
@Preview(
    apiLevel = 33,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AddEditHeartRateContentPreview() {
    SportAndHealthManagerTheme {
        Surface {
            AddEditBloodPressureContent(
                bloodPressureSystolicState = 0,
                bloodPressureDiastolicState = 0,
                selectedTimestamp = OffsetDateTime.now(),
                onDiastolicChange = {},
                onSystolicChange = {},
                onTimestampChange = {}
            )
        }
    }
}