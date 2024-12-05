package com.am_apps.recorded_money.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val description:String,
    val spentMoney:Double,
    val date:String,
    val time:String,
    val recordId:Long
)