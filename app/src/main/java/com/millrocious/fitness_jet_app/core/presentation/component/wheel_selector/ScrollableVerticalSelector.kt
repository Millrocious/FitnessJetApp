package com.millrocious.fitness_jet_app.core.presentation.component.wheel_selector

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ScrollableVerticalSelector(
    modifier: Modifier = Modifier,
    startIndex: Int,
    count: Int = 300,
    onItemSelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val scope = rememberCoroutineScope()
    var currentIndex by remember { mutableIntStateOf(startIndex) }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        VerticalWheelPicker(
            startIndex = startIndex,
            state = lazyListState,
            count = count,
            itemHeight = 50.dp,
            visibleItemCount = 3,
            onScrollFinish = {
                currentIndex = it
                onItemSelected(currentIndex)
            }
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { scope.launch { lazyListState.animateScrollToItem(index) } },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = index.toString(),
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .width(50.dp)
                .padding(vertical = 5.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp))
            )
        }
    }
}


@Preview
@Composable
fun ScrollableVerticalSelectorPrev() {
    ScrollableVerticalSelector(
        onItemSelected = {},
        startIndex = 60,
    )
}