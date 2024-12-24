package com.am_apps.recorded_money.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["recordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["recordId"])]
)
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val description:String,
    val spentMoney:Double,
    val date:String,
    val time:String,
    val recordId:Long
)