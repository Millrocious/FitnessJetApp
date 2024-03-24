package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.heart_rates_list.component

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
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.core.presentation.component.IconBox
import com.millrocious.fitness_jet_app.core.presentation.component.list.ListItemBody
import com.millrocious.fitness_jet_app.ui.theme.SportAndHealthManagerTheme
import java.time.OffsetDateTime

@Composable
fun HeartRateItem(
    heartRate: HeartRate,
    modifier: Modifier = Modifier,
) {
    ListItemBody(
        modifier = modifier,
        timestamp = heartRate.selectedTimestamp,
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
            text = "${heartRate.heartBeats}",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = " bpm",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

//@Preview(apiLevel = 33)
@Preview(apiLevel = 33, name = "Light", showBackground = true)
@Preview(apiLevel = 33, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@PreviewLightDark
@Composable
fun HeartRateItemPreview() {
    SportAndHealthManagerTheme {
        Surface {
            HeartRateItem(heartRate = HeartRate(heartBeats = 60, selectedTimestamp = OffsetDateTime.now()))
        }
    }
}