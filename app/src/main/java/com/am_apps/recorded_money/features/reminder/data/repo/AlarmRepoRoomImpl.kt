package com.am_apps.recorded_money.features.reminder.data.repo

import com.am_apps.recorded_money.core.domain.mapper.toRecordModel
import com.am_apps.recorded_money.core.domain.mapper.toTaskAlarmModel
import com.am_apps.recorded_money.core.domain.mapper.toTaskModel
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.reminder.domain.repo.AlarmRepo
import javax.inject.Inject

class AlarmRepoRoomImpl @Inject constructor(
    private val recordDoa: RecordDoa
) : AlarmRepo {
    override suspend fun deleteAlarm(taskId: Long) {
        recordDoa.deleteAlarm(taskId)
    }

    override suspend fun fetchTask(taskId: Long):TaskModel {
        return recordDoa.fetchTask(taskId).toTaskModel()
    }

    override suspend fun fetchAlarms(): List<TaskAlarmModel> {
        return recordDoa.fetchAllAlarms().map { alarmEntity ->
            alarmEntity.toTaskAlarmModel()
        }
    }
}