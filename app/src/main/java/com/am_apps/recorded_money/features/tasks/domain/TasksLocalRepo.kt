package com.am_apps.recorded_money.features.tasks.domain

import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.core.domain.model.TaskModel

interface TasksLocalRepo {
    suspend fun fetchTasks(recordId: Long):List<TaskModel>
    suspend fun addTask(task: TaskModel)
    suspend fun deleteTask(task: TaskModel)
    suspend fun setReminder(alarmModel: TaskAlarmModel)
    suspend fun cancelReminder(taskId: Long)
    fun hasReminder(taskId:Long):Boolean
}