package com.am_apps.recorded_money.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.am_apps.recorded_money.core.presentation.navigation.AppNavHost
import com.am_apps.recorded_money.features.reminder.TaskNotification
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            grantPermissions()
        }
        enableEdgeToEdge()
        setContent {
            RecordedMoneyTheme {
                AppNavHost()
            }
        }
    }

    private suspend fun grantPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                TaskNotification.TASK_NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        }else{
            TaskNotification.notification_permission_status.value = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == TaskNotification.TASK_NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                TaskNotification.notification_permission_status.value = true
            }
        }
    }
}