package com.am_apps.recorded_money.core.domain.mapper

import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.db.entity.TaskEntity

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        id, description, spentMoney, date, time, recordId
    )
}
fun TaskModel.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id, description, spentMoney, date, time, recordId
    )
}