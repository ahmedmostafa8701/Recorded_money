package com.am_apps.recorded_money.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TaskAlarmEntity(
    @PrimaryKey()
    val taskId: Long,
    val date: String,
    val time: String,
)