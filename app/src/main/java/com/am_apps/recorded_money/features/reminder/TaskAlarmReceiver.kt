package com.am_apps.recorded_money.features.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.am_apps.recorded_money.core.data.LocalRepoRoomImpl
import com.am_apps.recorded_money.core.domain.repo.LocalRepo
import com.am_apps.recorded_money.db.RecordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskAlarmReceiver : BroadcastReceiver() {
    lateinit var localRepo: LocalRepo
    override fun onReceive(context: Context, intent: Intent) {
        localRepo = LocalRepoRoomImpl(
            Room.databaseBuilder(context, RecordDatabase::class.java, "recorded_money_db").build()
                .recordDoa()
        )
        val taskId = intent.getLongExtra(TASK_ID_KEY, -1)
        if (taskId != -1L) {
            CoroutineScope(Dispatchers.IO).launch {
                val task = localRepo.fetchTask(taskId)
                TaskNotification(context.applicationContext, task).notifyTask()
            }
        }
    }

    companion object {
        const val TASK_ID_KEY = "task id key"
    }
}