package com.am_apps.recorded_money

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.am_apps.recorded_money.features.reminder.services.RescheduleAlarmService

class BootReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val serviceIntent = Intent(context, RescheduleAlarmService::class.java)
            context.startForegroundService(serviceIntent)
        }
    }
}