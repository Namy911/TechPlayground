package com.wpay.test_dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.wpay.common.util.DefaultDispatchers
import com.wpay.common.util.DispatcherProvider
import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.repository.UserRepository
import com.wpay.test_dagger.ui.navigation.AppNavigation
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.test_dagger.ui.viewmodel.UserViewModelFactory
import com.wpay.test_dagger.util.NetworkManager
import com.wpay.userstatistics.db.UserDatabase
import com.wpay.userstatistics.repository.StatisticsRepository
import com.wpay.userstatistics.viewmodel.StatisticsViewModel
import com.wpay.userstatistics.viewmodel.StatisticsViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var database: UserDatabase
    private lateinit var networkManager: NetworkManager
    private lateinit var repository: StatisticsRepository
    private lateinit var fakeApiService: FakeApiService
    private val dispatcherProvider: DispatcherProvider = DefaultDispatchers()

    private val statisticsViewModel: StatisticsViewModel by viewModels {
        StatisticsViewModelFactory(repository, dispatcherProvider)
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            UserRepository(fakeApiService, repository),
            dispatcherProvider,
            networkManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkManager = NetworkManager(applicationContext)
        fakeApiService = FakeApiService(applicationContext)
        database = UserDatabase.getInstance(applicationContext)
        repository = StatisticsRepository(database)

        setContent {
            AppNavigation(userViewModel, statisticsViewModel)
        }
    }
}
