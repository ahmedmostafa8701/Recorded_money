package com.am_apps.recorded_money.features.reminder.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.features.reminder.TaskReminder
import com.am_apps.recorded_money.features.reminder.domain.repo.AlarmRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RescheduleAlarmService : Service() {
    @Inject lateinit var alarmRepo: AlarmRepo
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, getString(R.string.task_reminder_channel_id))
            .setContentTitle(getString(R.string.reschedule_alarm))
            .setContentText(getString(R.string.reschedule_alarm_desc))
            .setSmallIcon(R.drawable.cash_icon)
            .build()
        startForeground(1, notification)
        serviceScope.launch {
            rescheduleAlarms()
        }
        return START_STICKY
    }
    private suspend fun rescheduleAlarms() {
        val alarms = alarmRepo.fetchAlarms()
        alarms.forEach{ alarm ->
            TaskReminder(this).setReminder(alarm)
        }
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}