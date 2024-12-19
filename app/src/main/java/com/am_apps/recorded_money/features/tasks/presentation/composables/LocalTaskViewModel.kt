package com.am_apps.recorded_money.features.tasks.presentation.composables

import androidx.compose.runtime.compositionLocalOf
import com.am_apps.recorded_money.features.tasks.presentation.view_model.TasksViewModel

val LocalTaskViewModel = compositionLocalOf<TasksViewModel> { error("no viewmodel exist") }