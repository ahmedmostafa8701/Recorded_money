package com.am_apps.recorded_money.core.domain.mapper

import com.am_apps.recorded_money.db.entity.TaskEntity
import com.am_apps.recorded_money.core.domain.model.TaskModel

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        id, description, spentMoney, date, recordId
    )
}
fun TaskModel.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id, description, spentMoney, date, recordId
    )
}