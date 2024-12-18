package com.am_apps.recorded_money.core.domain.repo

import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.core.domain.model.TaskModel

interface LocalRepo {
    suspend fun fetchTask(taskId:Long):TaskModel
    suspend fun fetchRecord(recordId:Long):RecordModel
}