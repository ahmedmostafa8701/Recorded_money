package com.am_apps.recorded_money.features.home_page.presentation.viewmodel

import com.am_apps.recorded_money.core.domain.model.RecordModel

sealed class RecordState() {
    data object Loading : RecordState()
    data object Initial : RecordState()
    data class Success(
        val records: List<RecordModel>,
        val totalCollectedMoney: Double,
        val totalSpentMoney: Double
    ) : RecordState()

    data class Error(val message: String) : RecordState()
}