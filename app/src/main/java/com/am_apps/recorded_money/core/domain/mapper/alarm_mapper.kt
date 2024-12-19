package com.am_apps.recorded_money.core.domain.mapper

import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.db.entity.TaskAlarmEntity

fun TaskAlarmEntity.toTaskAlarmModel() = TaskAlarmModel(
    taskId, date, time
)

fun TaskAlarmModel.toTaskAlarmEntity() = TaskAlarmEntity(
    taskId, date, time
)