package com.am_apps.recorded_money.features.tasks.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.config.responsiveHeight
import com.am_apps.recorded_money.config.responsiveWidth
import com.am_apps.recorded_money.core.presentation.composables.CustomAppBar
import com.am_apps.recorded_money.core.presentation.composables.CustomFab
import com.am_apps.recorded_money.db.RecordDatabase
import com.am_apps.recorded_money.features.home_page.presentation.composables.TotalMoneyDetails
import com.am_apps.recorded_money.features.tasks.data.TaskLocalRepoImpl
import com.am_apps.recorded_money.features.tasks.presentation.composables.LocalTaskViewModel
import com.am_apps.recorded_money.features.tasks.presentation.composables.TaskDialog
import com.am_apps.recorded_money.features.tasks.presentation.composables.TaskListView
import com.am_apps.recorded_money.features.tasks.presentation.view_model.TaskDialogState
import com.am_apps.recorded_money.features.tasks.presentation.view_model.TasksViewModel
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun TasksPage(
    modifier: Modifier = Modifier,
) {
    val viewModel = LocalTaskViewModel.current
    val tasksState = viewModel.tasksState.collectAsState()
    val dialogState = viewModel.dialogState.collectAsState()
    Scaffold(
        topBar = { CustomAppBar(title = stringResource(id = R.string.tasks)) },
        floatingActionButton = {
            CustomFab {
                viewModel.showAddDialog()
            }
        },
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { padding ->
        val paddingVertical = responsiveHeight(5)
        val paddingHorizontal = responsiveWidth(5)
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(
                    vertical = paddingVertical,
                    horizontal = paddingHorizontal
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TotalMoneyDetails(totalCollectedMoney = 20.0, totalSpentMoney = 20.0)
            TaskListView(
                tasks = tasksState.value,
                onDelete = { viewModel.deleteTask(it) },
                onUpdate = { viewModel.showUpdateDialog(it) },
            )
        }
    }
    if (dialogState.value is TaskDialogState.Enable) {
        TaskDialog(
            onConfirm = { task ->
                viewModel.addTask(task)
            }, onDismissRequest = {
                viewModel.dismissDialog()
            }, taskDialogState = dialogState.value as TaskDialogState.Enable
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun TasksPagePreview() {
    RecordedMoneyTheme {
        val db = Room.databaseBuilder<RecordDatabase>(
            LocalContext.current,
            RecordDatabase::class.java,
            "recorded_money_db"
        ).build()
        val repo = TaskLocalRepoImpl(db.recordDoa(), LocalContext.current)
        val viewModel = TasksViewModel(repo, SavedStateHandle())
        CompositionLocalProvider(LocalTaskViewModel provides viewModel) {
            TasksPage()
        }
    }
}