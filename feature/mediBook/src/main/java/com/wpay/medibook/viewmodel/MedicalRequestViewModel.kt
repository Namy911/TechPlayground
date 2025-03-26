package com.wpay.medibook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.core.di.FullNameValidatorQualifier
import com.wpay.core.domain.validation.Validator
import com.wpay.medibook.data.model.MedicalRequestEffect
import com.wpay.medibook.data.model.MedicalRequestEvent
import com.wpay.medibook.data.model.MedicalRequestState
import com.wpay.medibook.data.model.RequestAppointment.REQUEST_PRESCRIPTION
import com.wpay.medibook.data.model.RequestAppointment.SCHEDULE_CONSULTATION
import com.wpay.medibook.di.LocationValidatorQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
                is MedicalRequestEvent.CancelRequest -> {
                    _uiEffect.emit(
                        MedicalRequestEffect.NavigateBack
                    )
                }

                is MedicalRequestEvent.RequestConsultation -> {
                    _uiEffect.emit(
                        MedicalRequestEffect.ShowRequestSuccess(SCHEDULE_CONSULTATION.value)
                    )
                }

                is MedicalRequestEvent.RequestPrescription -> {
                    _uiEffect.emit(
                        MedicalRequestEffect.ShowRequestSuccess(REQUEST_PRESCRIPTION.value)
                    )
                }

                is MedicalRequestEvent.DateSelected -> TODO()
                is MedicalRequestEvent.OpenDatePicker -> {
                    _uiEffect.emit(
                        MedicalRequestEffect.ShowDatePicker
                    )
                }
                is MedicalRequestEvent.MedicalCenterChanged -> medicalCenterChanged(event.name)
                is MedicalRequestEvent.SpecialistNameChanged -> consultSpecialistNameChanged(
                    name = event.name,
                    requestId = event.requestId
                )

                is MedicalRequestEvent.ConsultationFormClicked -> {
                    switchActiveForm()
                }

                is MedicalRequestEvent.PrescriptionFormClicked -> {
                    switchActiveForm()
                }
            }
        }
    }

    fun onRequestSelected(actionId: String) {
        when (actionId) {
            SCHEDULE_CONSULTATION.value -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        consultExpandForm = true
                    )
                }
            }

            REQUEST_PRESCRIPTION.value -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        prescriptExpandForm = true,
                    )
                }
            }
        }
    }

    private fun consultSpecialistNameChanged(name: String, requestId: String) {
        if (requestId == SCHEDULE_CONSULTATION.value) {
            _uiState.update { currentState ->
                currentState.copy(
                    consultSpecialistName = name
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    prescriptSpecialistName = name
                )
            }
        }

    }

    private fun medicalCenterChanged(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                medicalCenter = name
            )
        }
    }

    private fun switchActiveForm() {
        _uiState.update { currentState ->
            currentState.copy(
                prescriptExpandForm = !currentState.prescriptExpandForm,
                consultExpandForm = !currentState.consultExpandForm
            )
        }
    }
}