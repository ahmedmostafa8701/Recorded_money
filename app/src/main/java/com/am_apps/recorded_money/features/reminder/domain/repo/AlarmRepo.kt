package com.am_apps.recorded_money.features.reminder.domain.repo

import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.core.domain.model.TaskModel

interface AlarmRepo {
    suspend fun deleteAlarm(taskId: Long)
    suspend fun fetchTask(taskId:Long):TaskModel
    suspend fun fetchAlarms():List<TaskAlarmModel>
}