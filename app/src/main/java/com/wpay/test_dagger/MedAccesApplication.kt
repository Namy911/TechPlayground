package com.wpay.test_dagger

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedAccesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}