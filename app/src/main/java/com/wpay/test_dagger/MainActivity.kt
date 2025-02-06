package com.wpay.test_dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.repository.UserRepository
import com.wpay.test_dagger.ui.navigation.AppNavigation
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.test_dagger.ui.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(FakeApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(viewModel)
        }
    }
}