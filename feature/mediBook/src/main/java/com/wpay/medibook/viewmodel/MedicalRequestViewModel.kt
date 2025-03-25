package com.wpay.medibook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.core.di.FullNameValidatorQualifier
import com.wpay.core.domain.validation.Validator
import com.wpay.medibook.data.model.MedicalRequestEffect
import com.wpay.medibook.data.model.MedicalRequestEvent
import com.wpay.medibook.data.model.MedicalRequestState
import com.wpay.medibook.di.LocationValidatorQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalRequestViewModel @Inject constructor(
    @FullNameValidatorQualifier private val fullNameValidator: Validator<String>,
    @LocationValidatorQualifier private val locationValidator: Validator<String>,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicalRequestState())
    val uiState: StateFlow<MedicalRequestState> = _uiState.asStateFlow()

    private val _uiEffect =
        MutableSharedFlow<MedicalRequestEffect>(replay = 0, extraBufferCapacity = 1)
    val uiEffect: SharedFlow<MedicalRequestEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: MedicalRequestEvent) {
        viewModelScope.launch {
            when (event) {
                is MedicalRequestEvent.CancelRequest -> TODO()
                is MedicalRequestEvent.RequestConsultation -> TODO()
                is MedicalRequestEvent.RequestPrescription -> TODO()
                is MedicalRequestEvent.DateSelected -> TODO()
                is MedicalRequestEvent.MedicalCenterSelected -> TODO()
                is MedicalRequestEvent.OpenDatePicker -> TODO()
                is MedicalRequestEvent.SpecialistSelected -> TODO()
            }
        }
    }

}