package com.am_apps.recorded_money.features.home_page.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.features.home_page.domain.RecordLocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val recordLocalRepo: RecordLocalRepo
) : ViewModel(){
    private val _recordsState = MutableStateFlow<RecordState>(RecordState.Initial)
    val recordsState = _recordsState.asStateFlow()
    private val _dialogState = MutableStateFlow<RecordDialogState>(RecordDialogState.Dismiss)
    val dialogState = _dialogState.asStateFlow()
    init {
        loadRecords()
    }
    private fun loadRecords(){
        viewModelScope.launch(Dispatchers.IO) {
            _recordsState.value = RecordState.Loading
            recordLocalRepo.getRecords().let { result ->
                val totalCollected = result.sumOf { it.collectedMoney }
                val totalSpent = result.sumOf { it.spentMoney }
                _recordsState.value = RecordState.Success(result, totalCollected, totalSpent)
            }
        }
    }
    fun addRecord(record: RecordModel){
        viewModelScope.launch(Dispatchers.IO) {
            recordLocalRepo.addRecord(record)
            loadRecords()
        }
    }

    fun deleteRecord(record: RecordModel) {
        viewModelScope.launch(Dispatchers.IO) {
            recordLocalRepo.deleteRecord(record)
            loadRecords()
        }
    }
    fun showAddDialog(){
        _dialogState.value = RecordDialogState.Enable()
    }
    fun showUpdateDialog(record:RecordModel){
        _dialogState.value = RecordDialogState.Enable(record)
    }
    fun dismissDialog(){
        _dialogState.value = RecordDialogState.Dismiss
    }
}