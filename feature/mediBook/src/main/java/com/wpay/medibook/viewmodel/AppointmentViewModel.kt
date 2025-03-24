package com.wpay.medibook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.core.domain.repository.ConsultationRepository
import com.wpay.core.domain.repository.PrescriptionRepository
import com.wpay.core.domain.repository.UserRepository

import com.wpay.medibook.data.model.AppointmentEffect
import com.wpay.medibook.data.model.AppointmentEvent
import com.wpay.medibook.data.model.AppointmentSate
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
class AppointmentViewModel @Inject constructor(
    private val consultationRepo: ConsultationRepository,
    private val prescriptionRepository: PrescriptionRepository,
    private val userRepo: UserRepository,
) : ViewModel(){

    private val _uiState = MutableStateFlow(AppointmentSate())
    val uiState: StateFlow<AppointmentSate> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<AppointmentEffect>(replay = 0, extraBufferCapacity = 1)
    val uiEffect: SharedFlow<AppointmentEffect> = _uiEffect.asSharedFlow()

//    val userId: StateFlow<Long?> = userRepo.userId

    init {
        viewModelScope.launch {
            userRepo.userId.collect { id ->
                _uiState.update {
                    it.copy(userName = id?.toString() ?: "Default Name")
                }
            }
        }
    }

    fun onEvent(event: AppointmentEvent){

    }

//    fun getPrescriptionsForUser(userId: Long) = prescriptionRepository.getPrescriptionsForUser(userId)
//
//    fun insertPrescription(prescription: Prescription) = prescriptionRepository.insertPrescription(prescription)
//
//    fun getAppointmentForUser(userId: Long) = appointmentRepo.getAppointmentForUser(userId)
//
//    fun insertAppointment(appointment: Appointment) = appointmentRepo.insertAppointment(appointment)
}