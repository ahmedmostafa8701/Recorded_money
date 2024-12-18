package com.am_apps.recorded_money.features.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.am_apps.recorded_money.core.domain.repo.LocalRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TaskAlarmReceiver @Inject constructor(
    private val localRepo: LocalRepo
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getLongExtra(TASK_ID_KEY, -1)
        if (taskId != -1L){
            GlobalScope.launch(Dispatchers.IO) {
                val task = localRepo.fetchTask(taskId)
                TaskNotification(context, task).notify()
            }
        }
    }
    companion object{
        const val TASK_ID_KEY = "task id key"
    }
}