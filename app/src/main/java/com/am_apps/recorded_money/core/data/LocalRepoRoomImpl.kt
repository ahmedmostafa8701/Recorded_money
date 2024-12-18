package com.am_apps.recorded_money.core.data

import com.am_apps.recorded_money.core.domain.mapper.toRecordModel
import com.am_apps.recorded_money.core.domain.mapper.toTaskModel
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.core.domain.repo.LocalRepo
import com.am_apps.recorded_money.db.doa.RecordDoa
import javax.inject.Inject

class LocalRepoRoomImpl @Inject constructor(
    private val recordDoa: RecordDoa
) : LocalRepo {
    override suspend fun fetchTask(taskId: Long):TaskModel {
        return recordDoa.fetchTask(taskId).toTaskModel()
    }

    override suspend fun fetchRecord(recordId: Long):RecordModel {
        return recordDoa.fetchRecord(recordId).toRecordModel()
    }
}