package com.millrocious.fitness_jet_app.feature_user.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.millrocious.fitness_jet_app.feature_user.domain.model.Gender
import com.millrocious.fitness_jet_app.feature_user.domain.model.UserInfo
import com.millrocious.fitness_jet_app.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: UserUseCases
): ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private var getUserInfoJob: Job? = null

    init {
        fetchUserInfo()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateHeight -> updateUserInfo(_state.value.copy(height = event.height))
            is ProfileEvent.UpdateWeight -> updateUserInfo(_state.value.copy(weight = event.weight))
            is ProfileEvent.UpdateAge -> updateUserInfo(_state.value.copy(age = event.age))
            is ProfileEvent.UpdateGender -> updateUserInfo(_state.value.copy(gender = event.gender))
            is ProfileEvent.UpdateStepsGoal -> updateUserInfo(_state.value.copy(stepsGoal = event.stepsGoal))
        }
    }

    private fun updateUserInfo(updatedState: ProfileState) {
        viewModelScope.launch {
            profileUseCases.updateUserInfo(
                UserInfo(
                    height = updatedState.height.toString(),
                    weight = updatedState.weight.toString(),
                    age = updatedState.age.toString(),
                    gender = updatedState.gender.toString(),
                    stepsGoal = updatedState.stepsGoal.toString()
                )
            )
        }
    }

    private fun fetchUserInfo() {
        getUserInfoJob?.cancel()
        getUserInfoJob = profileUseCases.getUserInfo()
            .onEach { userInfo ->
                _state.value = _state.value.copy(
                    height = userInfo.height.toInt(),
                    weight = userInfo.weight.toFloat(),
                    gender = Gender.valueOf(userInfo.gender),
                    age = userInfo.age.toInt(),
                    stepsGoal = userInfo.stepsGoal.toInt()
                )
            }
            .launchIn(viewModelScope)
    }
}