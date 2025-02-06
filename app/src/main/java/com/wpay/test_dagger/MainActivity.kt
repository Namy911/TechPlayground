package com.wpay.test_dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.repository.UserRepository
import com.wpay.test_dagger.ui.theme.Test_daggerTheme
import com.wpay.test_dagger.ui.theme.UserListScreen
import com.wpay.test_dagger.ui.theme.viewmodel.UserViewModel
import com.wpay.test_dagger.ui.theme.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(FakeApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListScreen(viewModel)
        }
    }
}