package com.am_apps.recorded_money.core.domain.mapper

import com.am_apps.recorded_money.db.entity.RecordEntity
import com.am_apps.recorded_money.core.domain.model.RecordModel

fun RecordEntity.toRecordModel(): RecordModel = RecordModel(
    id, name, collectedMoney, spentMoney
)
fun RecordModel.toRecordEntity(): RecordEntity = RecordEntity(
    id, name, collectedMoney, spentMoney
)