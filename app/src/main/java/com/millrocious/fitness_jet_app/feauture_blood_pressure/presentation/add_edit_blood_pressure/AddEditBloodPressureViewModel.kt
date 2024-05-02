package com.millrocious.fitness_jet_app.feauture_blood_pressure.presentation.add_edit_blood_pressure

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.model.BloodPressure
import com.millrocious.fitness_jet_app.feauture_blood_pressure.domain.use_case.BloodPressureUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditBloodPressureViewModel @Inject constructor(
    private val bloodPressureUseCases: BloodPressureUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _bloodPressureSystolic = mutableIntStateOf(120)
    val bloodPressureSystolic: State<Int> = _bloodPressureSystolic

    private val _bloodPressureDiastolic = mutableIntStateOf(80)
    val bloodPressureDiastolic: State<Int> = _bloodPressureDiastolic

    private val _selectedTimestamp = mutableStateOf(OffsetDateTime.now())
    val selectedTimestamp: State<OffsetDateTime> = _selectedTimestamp

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentBloodPressureId: String? = null

    fun getCurrentHeartRateId(): String? {
        return currentBloodPressureId
    }

    init {
        savedStateHandle.get<String>("bloodPressureId")?.let { bloodPressureId ->
            Log.d("BLOOD_PRESSURE", bloodPressureId)
            if (bloodPressureId != "") {
                viewModelScope.launch {
                    bloodPressureUseCases.getBloodPressure(bloodPressureId)?.also {
                        currentBloodPressureId = it.uuid
                        _bloodPressureDiastolic.intValue = it.diastolic
                        _bloodPressureSystolic.intValue = it.systolic
                        _selectedTimestamp.value = it.selectedTimestamp
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditBloodPressureEvent) {
        when (event) {
            is AddEditBloodPressureEvent.UpdateBloodPressureSystolic -> {
                _bloodPressureSystolic.intValue = event.value
            }

            is AddEditBloodPressureEvent.UpdateBloodPressureDiastolic -> {
                _bloodPressureDiastolic.intValue = event.value
            }

            is AddEditBloodPressureEvent.SaveBloodPressure -> {
                viewModelScope.launch {
                    val newBloodPressure = currentBloodPressureId?.let {
                        BloodPressure(
                            systolic = bloodPressureSystolic.value,
                            diastolic = bloodPressureDiastolic.value,
                            selectedTimestamp = selectedTimestamp.value,
                            uuid = it
                        )
                    } ?: BloodPressure(
                        systolic = bloodPressureSystolic.value,
                        diastolic = bloodPressureDiastolic.value,
                        selectedTimestamp = selectedTimestamp.value
                    )

                    bloodPressureUseCases.addBloodPressure(
                        newBloodPressure
                    )

                    _eventFlow.emit(UiEvent.SaveHeartRate)
                }
            }

            is AddEditBloodPressureEvent.UpdateTimestamp -> {
                _selectedTimestamp.value = event.value
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveHeartRate : UiEvent()
    }

}