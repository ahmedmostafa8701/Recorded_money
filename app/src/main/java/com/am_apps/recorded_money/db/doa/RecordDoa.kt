package com.am_apps.recorded_money.db.doa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.am_apps.recorded_money.db.entity.RecordEntity
import com.am_apps.recorded_money.db.entity.TaskAlarmEntity
import com.am_apps.recorded_money.db.entity.TaskEntity

@Dao
interface RecordDoa {
    @Upsert
    suspend fun insertRecord(record: RecordEntity)

    @Upsert
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteRecord(record: RecordEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM RecordEntity")
    suspend fun fetchAllRecords(): List<RecordEntity>

    @Query("SELECT * FROM TaskEntity where recordId = :recordId")
    suspend fun fetchAllTasks(recordId: Long): List<TaskEntity>

    @Query("SELECT sum(collectedMoney) FROM RecordEntity")
    suspend fun getCollectedMoney(): Double

    @Query("SELECT sum(spentMoney) FROM RecordEntity")
    suspend fun getSpentMoney(): Double

    @Query("SELECT * From TaskEntity where id = :taskId")
    suspend fun fetchTask(taskId: Long): TaskEntity

    @Query("SELECT * From RecordEntity where id = :recordId")
    suspend fun fetchRecord(recordId: Long): RecordEntity

    @Upsert
    suspend fun insertAlarm(taskAlarmEntity: TaskAlarmEntity)

    @Query("DELETE FROM TaskAlarmEntity WHERE taskId = :taskId")
    suspend fun deleteAlarm(taskId: Long)

    @Query("SELECT * FROM TaskAlarmEntity")
    suspend fun fetchAllAlarms(): List<TaskAlarmEntity>
}