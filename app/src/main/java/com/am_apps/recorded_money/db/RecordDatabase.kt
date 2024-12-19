package com.am_apps.recorded_money.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.db.entity.RecordEntity
import com.am_apps.recorded_money.db.entity.TaskAlarmEntity
import com.am_apps.recorded_money.db.entity.TaskEntity

@Database(entities = [RecordEntity::class, TaskEntity::class, TaskAlarmEntity::class], version = 1, exportSchema = false)
abstract class RecordDatabase : RoomDatabase(){
    abstract fun recordDoa(): RecordDoa
}