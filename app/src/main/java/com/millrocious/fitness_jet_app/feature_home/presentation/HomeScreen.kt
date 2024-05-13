package com.millrocious.fitness_jet_app.feature_home.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.millrocious.fitness_jet_app.core.presentation.component.bottom_bar.BottomBar
import com.millrocious.fitness_jet_app.core.presentation.util.Screen
import com.millrocious.fitness_jet_app.feature_home.presentation.component.RunList
import com.millrocious.fitness_jet_app.feature_user.presentation.sign_in.UserData
import kotlin.math.roundToInt


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    userData: UserData?,
) {
    val state by viewModel.state

    val scrollState = rememberLazyListState()

    val shouldHideBottomBar by remember(scrollState) {
        derivedStateOf {
            val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset
            firstVisibleItemIndex != 0 || firstVisibleItemScrollOffset != 0
        }
    }

    val pxToMove = with(LocalDensity.current) {
        100.dp.toPx().roundToInt()
    }


    val offset by animateIntOffsetAsState(
        if (shouldHideBottomBar) { IntOffset(0, pxToMove) } else { IntOffset.Zero },
        label = "offset"
    )

    val animatedPadding by animateDpAsState(
        if (shouldHideBottomBar) { 0.dp } else { 100.dp }, label = "",
        animationSpec = tween(400)
    )

    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .offset {offset}
                    .animateContentSize()
                    .height(animatedPadding),
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.MapScreen.route) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.DirectionsRun, contentDescription = null)
            }
        }
    ) {
        RunList(
            modifier = Modifier.padding(),
            state = state,
            scrollState = scrollState,
            userData = userData
        )
    }
}