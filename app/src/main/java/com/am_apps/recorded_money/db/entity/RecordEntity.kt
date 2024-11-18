package com.am_apps.recorded_money.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val collectedMoney:Double,
    val spentMoney:Double
)