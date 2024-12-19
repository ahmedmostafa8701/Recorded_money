package com.am_apps.recorded_money.features.tasks.presentation.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am_apps.recorded_money.core.domain.model.TaskAlarmModel
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.features.tasks.domain.TasksLocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksLocalRepo: TasksLocalRepo,
    stateHandle: SavedStateHandle
):ViewModel() {

    val recordId: Long? = stateHandle["recordId"]
    private val _tasksState = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasksState = _tasksState.asStateFlow()
    private val _dialogState = MutableStateFlow<TaskDialogState>(TaskDialogState.Dismiss)
    val dialogState = _dialogState.asStateFlow()
    init {
        loadTasks()
    }

    fun addTask(task: TaskModel) {
        viewModelScope.launch {
            tasksLocalRepo.addTask(task)
            loadTasks()
        }
    }
    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            tasksLocalRepo.deleteTask(task)
            loadTasks()
        }
    }
    private fun loadTasks() {
        viewModelScope.launch {
            recordId?.let { _tasksState.value = tasksLocalRepo.fetchTasks(it) }
        }
    }
    fun showAddDialog(){
        recordId?.let {
            _dialogState.value = TaskDialogState.Enable(it)
        }
    }
    fun showUpdateDialog(task: TaskModel){
        recordId?.let {
            _dialogState.value = TaskDialogState.Enable(it, task)
        }
    }
    fun dismissDialog(){
        _dialogState.value = TaskDialogState.Dismiss
    }
    fun setReminder(alarmModel: TaskAlarmModel){
        viewModelScope.launch {
            tasksLocalRepo.setReminder(alarmModel)
        }
    }
    fun cancelReminder(taskId: Long){
        viewModelScope.launch {
            tasksLocalRepo.cancelReminder(taskId)
        }
    }
    fun hasReminder(taskId:Long):Boolean {
        return tasksLocalRepo.hasReminder(taskId)
    }
}