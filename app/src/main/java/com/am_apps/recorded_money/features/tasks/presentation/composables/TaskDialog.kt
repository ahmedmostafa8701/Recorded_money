package com.am_apps.recorded_money.features.tasks.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.core.presentation.composables.CustomIcon
import com.am_apps.recorded_money.datePicker
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordDialogState
import com.am_apps.recorded_money.features.tasks.presentation.view_model.TaskDialogState
import com.am_apps.recorded_money.timePicker
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun TaskDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: (task: TaskModel) -> Unit,
    taskDialogState: TaskDialogState.Enable
) {
    val buttonSuccessName = if (taskDialogState.enableType == RecordDialogState.ADD_TYPE){
        stringResource(id = R.string.add)
    }else{
        stringResource(id = R.string.update)
    }
    val addRecord: () -> Unit = {
        val task = taskDialogState.getTask()
        task?.let {
            onConfirm(it)
            onDismissRequest()
        }
    }
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(responsiveSize(20))
                )
                .padding(vertical = responsiveHeight(10), horizontal = responsiveHeight(20))
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(responsiveHeight(10))) {
                Text(
                    text = stringResource(id = R.string.add_task),
                    color = MaterialTheme.colorScheme.primary
                )
                TextField(
                    value = taskDialogState.recordDescription.value,
                    onValueChange = {
                        taskDialogState.recordDescription.value = it
                        taskDialogState.descriptionError.value = false
                    },
                    label = { Text(stringResource(id = R.string.task_name)) },
                    isError = taskDialogState.descriptionError.value
                )
                TextField(
                    value = taskDialogState.recordDate.value,
                    onValueChange = {},
                    label = { Text(stringResource(id = R.string.date)) },
                    trailingIcon = {
                        Icon(Icons.Filled.DateRange, contentDescription = null, modifier = Modifier.clickable {
                            datePicker(context){taskDialogState.recordDate.value = it}
                        })
                    },
                    isError = taskDialogState.dateError.value,
                    readOnly = true
                )
                TextField(
                    value = taskDialogState.recordTime.value,
                    onValueChange = {},
                    label = { Text(stringResource(id = R.string.time)) },
                    trailingIcon = {
                        CustomIcon(iconId = R.drawable.clock, color = Color.Black, clickable = {
                            timePicker(context){taskDialogState.recordTime.value = it}
                        })
                    },
                    isError = taskDialogState.timeError.value,
                    readOnly = true
                )

                TextField(
                    value = taskDialogState.recordSpentMoney.value,
                    onValueChange = {
                        taskDialogState.recordSpentMoney.value = it
                        taskDialogState.spentError.value = false
                    },
                    label = { Text(stringResource(id = R.string.record_spent_money)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = taskDialogState.spentError.value
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(responsiveWidth(10)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismissRequest,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Button(onClick = addRecord, modifier = Modifier.weight(1f)) {
                        Text(text = buttonSuccessName)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, locale = "en")
fun TaskDialogPreview() {
    RecordedMoneyTheme {
        Scaffold { padding ->
            TaskDialog(
                onDismissRequest = {},
                onConfirm = {},
                taskDialogState = TaskDialogState.Enable(recordId = 0),
                modifier = Modifier.padding(padding)
            )
        }
    }
}