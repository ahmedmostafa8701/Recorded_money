package com.am_apps.recorded_money.features.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.am_apps.recorded_money.db.RecordDatabase
import com.am_apps.recorded_money.features.reminder.data.repo.AlarmRepoRoomImpl
import com.am_apps.recorded_money.features.reminder.domain.repo.AlarmRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskAlarmReceiver : BroadcastReceiver() {
    private lateinit var alarmRepo: AlarmRepo
    override fun onReceive(context: Context, intent: Intent) {
        alarmRepo = AlarmRepoRoomImpl(
            Room.databaseBuilder(context, RecordDatabase::class.java, "recorded_money_db").build()
                .recordDoa()
        )
        val taskId = intent.getLongExtra(TASK_ID_KEY, -1)
        if (taskId != -1L) {
            CoroutineScope(Dispatchers.IO).launch {
                val task = alarmRepo.fetchTask(taskId)
                TaskNotification(context.applicationContext, task).notifyTask()
                alarmRepo.deleteAlarm(task.id)
                TaskReminder(context.applicationContext).cancelReminder(task.id)
            }
        }
    }

    companion object {
        const val TASK_ID_KEY = "task id key"
    }
}