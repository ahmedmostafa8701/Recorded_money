package com.am_apps.recorded_money.features.home_page.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.validateOnlyDoubles

sealed class RecordDialogState {
    companion object{
        private const val NEW_RECORD_ID = 0L
        const val ADD_TYPE = "add"
        const val UPDATE_TYPE = "update"
    }
    data object Dismiss : RecordDialogState()
    data class Enable(
        private val record: RecordModel = RecordModel(0, "", 0.0, 0.0)
    ):RecordDialogState() {
        val enableType = if(record.id == NEW_RECORD_ID) ADD_TYPE else UPDATE_TYPE
        var recordName = mutableStateOf(record.name)
        var recordCollectedMoney = mutableStateOf(record.collectedMoney.toString())
        var recordSpentMoney = mutableStateOf(record.spentMoney.toString())
        var nameError = mutableStateOf(false)
        var collectedError = mutableStateOf(false)
        var spentError = mutableStateOf(false)
        fun getRecord():RecordModel? {
            if(!validate()) return null
            return RecordModel(
                record.id,
                recordName.value,
                recordCollectedMoney.value.toDouble(),
                recordSpentMoney.value.toDouble()
            )
        }
        private fun validate():Boolean {
            if (recordName.value.isEmpty()) {
                nameError.value = true
            }
            if (!validateOnlyDoubles(recordCollectedMoney.value)) {
                collectedError.value = true
            }
            if (!validateOnlyDoubles(recordSpentMoney.value)) {
                spentError.value = true
            }
            return !nameError.value && !collectedError.value && !spentError.value
        }
    }
}