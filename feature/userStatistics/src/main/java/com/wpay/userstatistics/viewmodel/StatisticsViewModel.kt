package com.wpay.userstatistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.common.util.DispatcherProvider
import com.wpay.common.data.database.entity.SessionDuration
import com.wpay.common.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val repository: StatisticsRepository,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _sessionDurations = MutableStateFlow<List<SessionDuration>>(emptyList())
    val sessionDurations = _sessionDurations.asStateFlow()

    fun loadUserSessions(userId: Int) {
        viewModelScope.launch(dispatcher.io) {
            _sessionDurations.value = repository.getUserSessions(userId)
        }
    }
}