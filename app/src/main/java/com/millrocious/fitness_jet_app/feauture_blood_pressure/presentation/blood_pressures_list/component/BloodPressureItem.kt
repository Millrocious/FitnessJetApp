package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.blood_pressures_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.millrocious.fitness_jet_app.core.presentation.component.IconBox
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.core.presentation.component.list.ListItemBody
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import java.time.OffsetDateTime

@Composable
fun BloodPressureItem(
    modifier: Modifier = Modifier,
    bloodPressure: BloodPressure,
) {
    ListItemBody(
        modifier = modifier,
        timestamp = bloodPressure.selectedTimestamp,
        iconBox = {
            IconBox(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.errorContainer),
                tintColor = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    ) {
        Text(
            text = "${bloodPressure.systolic}/${bloodPressure.diastolic}",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = " mmHg",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
fun BloodPressureItemPreview() {
    SportAndHealthManagerTheme {
        Surface {
            BloodPressureItem(
                bloodPressure = BloodPressure(
                    diastolic = 120,
                    systolic = 60,
                    selectedTimestamp = OffsetDateTime.now()
                )
            )
        }
    }
}