package com.millrocious.fitness_jet_app.feature_map_tracker.framework.tracker

import com.millrocious.fitness_jet_app.feature_map_tracker.di.ApplicationScope
import com.millrocious.fitness_jet_app.feature_map_tracker.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimeTracker @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    private var timeElapsed = 0L

    private var isRunning = false

    private var callback: ((timeInMillis: Long) -> Unit)? = null

    private var job: Job? = null

    private fun start() {
        if (job != null)
            return
        this.job = applicationScope.launch(defaultDispatcher) {
            while (isRunning && isActive) {
                callback?.invoke(timeElapsed)
                timeElapsed += 1000
                delay(1000)
            }
        }
    }

    fun startResumeTimer(callback: (timeInMillis: Long) -> Unit) {
        if (isRunning)
            return
        this.callback = callback
        isRunning = true
        start()
    }

    fun stopTimer() {
        pauseTimer()
        timeElapsed = 0L
    }

    fun pauseTimer() {
        isRunning = false

        job?.cancel()
        job = null

        callback = null
    }
}