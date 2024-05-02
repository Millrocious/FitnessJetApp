package com.millrocious.fitness_jet_app.feauture_heart_rate.presentation.add_edit_heart_rate

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.model.HeartRate
import com.millrocious.fitness_jet_app.feauture_heart_rate.domain.use_case.HeartRateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditHeartRateViewModel @Inject constructor(
    private val heartRateUseCases: HeartRateUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _heartRate = mutableIntStateOf(0)
    val heartRate: State<Int> = _heartRate

    private val _selectedTimestamp = mutableStateOf(OffsetDateTime.now())
    val selectedTimestamp: State<OffsetDateTime> = _selectedTimestamp

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentHeartRateId: String? = null

    fun getCurrentHeartRateId(): String? {
        return currentHeartRateId
    }

    init {
        savedStateHandle.get<String>("heartRateId")?.let { heartRateId ->
            Log.d("HEART_RATE", heartRateId)
            Log.d("HEART_RATE_ID", heartRateId)
            Log.d("HEART_RATE_ID", (heartRateId != "").toString())
            if (heartRateId != "") {
                viewModelScope.launch {
                    heartRateUseCases.getHeartRate(heartRateId)?.also {
                        currentHeartRateId = it.uuid
                        _heartRate.intValue = it.heartBeats
                        _selectedTimestamp.value = it.selectedTimestamp
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditHeartRateEvent) {
        when (event) {
            is AddEditHeartRateEvent.UpdateHeartRate -> {
                _heartRate.intValue = event.value
            }

            is AddEditHeartRateEvent.SaveHeartRate -> {
                viewModelScope.launch {

                    val newHeartRate = currentHeartRateId?.let {
                        HeartRate(
                            heartBeats = heartRate.value,
                            selectedTimestamp = selectedTimestamp.value,
                            uuid = it
                        )
                    } ?: HeartRate(
                        heartBeats = heartRate.value,
                        selectedTimestamp = selectedTimestamp.value
                    )

                    heartRateUseCases.addHeartRate(newHeartRate)


                    _eventFlow.emit(UiEvent.SaveHeartRate)
                }
            }

            is AddEditHeartRateEvent.UpdateTimestamp -> {
                _selectedTimestamp.value = event.value
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveHeartRate : UiEvent()
    }

}

data class User(
    val username: String? = null, val email: String? = null
)