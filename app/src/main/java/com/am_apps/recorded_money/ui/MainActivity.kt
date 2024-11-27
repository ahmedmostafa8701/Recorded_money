package com.am_apps.recorded_money.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.am_apps.recorded_money.core.presentation.navigation.AppNavHost
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecordedMoneyTheme {
                AppNavHost()
            }
        }
    }
}