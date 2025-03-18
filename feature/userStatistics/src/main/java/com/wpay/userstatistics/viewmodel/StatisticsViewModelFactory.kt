package com.wpay.userstatistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wpay.core.util.DispatcherProvider
import com.wpay.core.data.repository.StatisticsRepository

class StatisticsViewModelFactory(
    private val repository: StatisticsRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatisticsViewModel(repository, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}