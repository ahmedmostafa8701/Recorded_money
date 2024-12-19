package com.am_apps.recorded_money.features.tasks.data

import android.content.Context
import com.am_apps.recorded_money.core.domain.mapper.toTaskAlarmEntity
import com.am_apps.recorded_money.core.domain.mapper.toTaskEntity
import com.am_apps.recorded_money.core.domain.mapper.toTaskModel
import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.reminder.TaskReminder
import com.am_apps.recorded_money.features.tasks.domain.TasksLocalRepo

class TaskLocalRepoImpl(private val recordDao: RecordDoa, private val context:Context): TasksLocalRepo{
    private val taskReminder = TaskReminder(context)
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

    override suspend fun setReminder(alarmModel: TaskAlarmModel) {
        taskReminder.setReminder(alarmModel)
        recordDao.insertAlarm(alarmModel.toTaskAlarmEntity())
    }

    override suspend fun cancelReminder(taskId: Long) {
        taskReminder.cancelReminder(taskId)
        recordDao.deleteAlarm(taskId)
    }

    override fun hasReminder(taskId: Long): Boolean {
        return taskReminder.hasReminder(taskId)
    }
}