package com.am_apps.recorded_money.features.tasks.data

import com.am_apps.recorded_money.core.domain.mapper.toTaskEntity
import com.am_apps.recorded_money.core.domain.mapper.toTaskModel
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.tasks.domain.TasksLocalRepo

class TaskLocalRepoImpl(private val recordDao: RecordDoa): TasksLocalRepo{
    override suspend fun fetchTasks(recordId: Long): List<TaskModel> {
        return recordDao.fetchAllTasks(recordId).map {
            it.toTaskModel()
        }
    }

    override suspend fun addTask(task: TaskModel) {
        recordDao.insertTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: TaskModel) {
        recordDao.deleteTask(task.toTaskEntity())
    }
}