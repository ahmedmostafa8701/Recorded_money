package com.am_apps.recorded_money.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.am_apps.recorded_money.core.presentation.navigation.AppNavHost
import com.am_apps.recorded_money.grantPermissions
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            grantPermissions(
                this, arrayListOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.SCHEDULE_EXACT_ALARM,
                    Manifest.permission.FOREGROUND_SERVICE,
                    Manifest.permission.WAKE_LOCK
                )
            )
        }
        enableEdgeToEdge()
        setContent {
            RecordedMoneyTheme {
                AppNavHost()
            }
        }
    }
}