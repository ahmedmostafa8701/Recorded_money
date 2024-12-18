package com.am_apps.recorded_money.features.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.getCalendarFromStrings

class TaskReminder(
    val context: Context,
    val taskModel: TaskModel
){
    private val alarmManager:AlarmManager? = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    fun setReminder(){
        val alarmIntent = Intent(context, TaskAlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, taskModel.id.toInt(), intent, 0)
        }
        val calendar = getCalendarFromStrings(taskModel.date, taskModel.time)
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }
    fun cancelReminder(){
        val alarmIntent = Intent(context, TaskAlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, taskModel.id.toInt(), intent, PendingIntent.FLAG_NO_CREATE)
        }
        if (alarmIntent != null && alarmManager != null) {
            alarmManager.cancel(alarmIntent)
        }
    }
}