package com.am_apps.recorded_money.features.tasks.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun TaskListView(
    modifier: Modifier = Modifier,
    tasks: List<TaskModel>,
    onDelete: (record: TaskModel) -> Unit,
    onUpdate: (record: TaskModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(tasks.size) {
            TaskCard(
                task = tasks[it],
                onDelete = { record ->
                    onDelete(record)
                },
                onUpdate = { record ->
                    onUpdate(record)
                },
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun TasksViewPreview() {
    RecordedMoneyTheme {
        TaskListView(tasks = listOf(
            TaskModel(1, "Task 1", 10.0, "2021-09-01", "10:30",1),
            TaskModel(1, "Task 2", 10.0, "2021-09-01", "10:30",1),
            TaskModel(1, "Task 3", 10.0, "2021-09-01", "10:30",1),
            TaskModel(1, "Task 4", 10.0, "2021-09-01", "10:30",1),
            TaskModel(1, "Task 5", 10.0, "2021-09-01", "10:30",1),
        ), onDelete = {}, onUpdate = {})
    }
}