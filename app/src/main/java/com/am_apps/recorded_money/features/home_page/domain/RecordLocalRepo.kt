package com.am_apps.recorded_money.features.home_page.domain

import com.am_apps.recorded_money.core.domain.model.RecordModel

interface RecordLocalRepo {
    suspend fun fetchRecords(): List<RecordModel>
    suspend fun addRecord(record: RecordModel)
    suspend fun deleteRecord(record: RecordModel)
}