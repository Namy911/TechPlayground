package com.wpay.test_dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wpay.common.ui.theme.Med_Access_Theme
import com.wpay.test_dagger.ui.navigation.RootNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Med_Access_Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            WindowInsets.statusBars.asPaddingValues()
                        ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNav()
                }
            }
        }
    }
}
