package com.am_apps.recorded_money.features.home_page.data

import com.am_apps.recorded_money.core.domain.mapper.toRecordEntity
import com.am_apps.recorded_money.core.domain.mapper.toRecordModel
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.attachments.domain.repo.AttachmentRepo
import com.am_apps.recorded_money.features.home_page.domain.RecordLocalRepo
import com.am_apps.recorded_money.features.tasks.domain.TasksLocalRepo
import javax.inject.Inject

class RecordLocalRepoRoomImpl @Inject constructor(private val recordDao: RecordDoa) :
    RecordLocalRepo {

    @Inject
    lateinit var attachmentRepo: AttachmentRepo
    @Inject
    lateinit var taskRepo: TasksLocalRepo

    override suspend fun fetchRecords(): List<RecordModel> {
        return recordDao.fetchAllRecords().map { it.toRecordModel() }
    }

    override suspend fun addRecord(record: RecordModel) {
        recordDao.insertRecord(record.toRecordEntity())
    }

    override suspend fun deleteRecord(record: RecordModel) {
        attachmentRepo.deleteAttachments(record.id.toString())
        taskRepo.fetchTasks(record.id).map { task -> taskRepo.deleteTask(task) }
        recordDao.deleteRecord(record.toRecordEntity())
    }
}
