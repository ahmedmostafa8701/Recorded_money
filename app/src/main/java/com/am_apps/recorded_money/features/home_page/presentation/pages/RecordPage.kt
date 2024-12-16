package com.am_apps.recorded_money.features.home_page.presentation.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.am_apps.recorded_money.core.presentation.navigation.RecordPageNavBar
import com.am_apps.recorded_money.core.presentation.navigation.RecordPageNavHost

@Composable
fun RecordPage(
    recordId: Long
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { RecordPageNavBar(navController, recordId) },
        modifier = Modifier.fillMaxSize().systemBarsPadding()
    ) { padding ->
        RecordPageNavHost(recordId = recordId, navController, modifier = Modifier.padding(padding))
    }
}