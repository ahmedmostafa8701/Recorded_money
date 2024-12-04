package com.am_apps.recorded_money.features.tasks.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.validateOnlyDoubles

sealed class TaskDialogState {
    companion object{
        private const val NEW_RECORD_ID = 0L
        const val ADD_TYPE = "add"
        const val UPDATE_TYPE = "update"
    }
    data object Dismiss : TaskDialogState()
    data class Enable(
        val recordId:Long,
        private val task: TaskModel = TaskModel(0, "", 0.0, "", "", recordId)
    ):TaskDialogState() {
        val enableType = if(task.id == NEW_RECORD_ID) ADD_TYPE else UPDATE_TYPE
        var recordDescription = mutableStateOf(task.description)
        var recordSpentMoney = mutableStateOf(task.spentMoney.toString())
        var recordDate = mutableStateOf(task.date)
        var recordTime = mutableStateOf(task.time)
        var descriptionError = mutableStateOf(false)
        var spentError = mutableStateOf(false)
        var dateError = mutableStateOf(false)
        var timeError = mutableStateOf(false)
        fun getTask():TaskModel? {
            if(!validate()) return null
            return TaskModel(
                task.id,
                recordDescription.value,
                recordSpentMoney.value.toDouble(),
                recordDate.value,
                recordTime.value,
                recordId
            )
        }
        private fun validate():Boolean {
            if (recordDescription.value.isEmpty()) {
                descriptionError.value = true
            }
            if (recordDate.value.isEmpty()) {
                dateError.value = true
            }
            if (recordTime.value.isEmpty()) {
                timeError.value = true
            }
            if (!validateOnlyDoubles(recordSpentMoney.value)) {
                spentError.value = true
            }
            return !descriptionError.value && !dateError.value && !spentError.value
        }
    }
}