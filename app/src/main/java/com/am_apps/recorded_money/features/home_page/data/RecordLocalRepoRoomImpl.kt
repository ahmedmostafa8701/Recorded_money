package com.am_apps.recorded_money.features.home_page.data

import com.am_apps.recorded_money.core.domain.mapper.toRecordEntity
import com.am_apps.recorded_money.core.domain.mapper.toRecordModel
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.home_page.domain.RecordLocalRepo
import javax.inject.Inject

class RecordLocalRepoRoomImpl @Inject constructor(private val recordDao: RecordDoa) : RecordLocalRepo {
    override suspend fun getRecords(): List<RecordModel> {
        return recordDao.fetchAllRecords().map { it.toRecordModel() }
    }

    override suspend fun addRecord(record: RecordModel) {
        recordDao.insertRecord(record.toRecordEntity())
    }

    override suspend fun deleteRecord(record: RecordModel) {
        recordDao.deleteRecord(record.toRecordEntity())
    }
}
