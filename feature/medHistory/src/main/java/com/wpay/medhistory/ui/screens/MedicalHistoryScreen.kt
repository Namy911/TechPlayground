package com.wpay.medhistory.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.common.ui.MedicalCardItem
import com.wpay.common.ui.sampleUsers


@Composable
fun MedicalHistoryScreen (navController: NavController){
    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(sampleUsers) { user ->
                MedicalCardItem(user, Modifier)
            }
        }
    }
}