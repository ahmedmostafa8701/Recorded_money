package com.am_apps.recorded_money.features.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.getCalendarFromStrings

class TaskReminder(
    val context: Context,
){
    private val alarmManager:AlarmManager? = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    fun setReminder(alarmModel: TaskAlarmModel){
        val alarmIntent = Intent(context, TaskAlarmReceiver::class.java).let { intent ->
            intent.putExtra(TaskAlarmReceiver.TASK_ID_KEY, alarmModel.taskId)
            PendingIntent.getBroadcast(context, alarmModel.taskId.toInt(), intent,
                PendingIntent.FLAG_IMMUTABLE)
        }
        val calendar = getCalendarFromStrings(alarmModel.date, alarmModel.time)
        alarmManager?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && canScheduleExactAlarms()){
                setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
            }else{
                set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
            }
        }
    }
    fun cancelReminder(taskId: Long){
        val alarmIntent = Intent(context, TaskAlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, taskId.toInt(), intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE)
        }
        if (alarmIntent != null && alarmManager != null) {
            alarmManager.cancel(alarmIntent)
        }
    }
    fun hasReminder(taskId:Long):Boolean {
        val alarmIntent = Intent(context, TaskAlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, taskId.toInt(), intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE)
        }
        return alarmIntent != null
    }
}