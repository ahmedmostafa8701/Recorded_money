package com.am_apps.recorded_money.features.home_page.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordDialogState

@Composable
fun RecordDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (record: RecordModel) -> Unit,
    recordDialogState: RecordDialogState.Enable = RecordDialogState.Enable()
) {
    val buttonSuccessName = if (recordDialogState.enableType == RecordDialogState.ADD_TYPE){
        stringResource(id = R.string.add)
    }else{
        stringResource(id = R.string.update)
    }
    val addRecord: () -> Unit = {
        val record = recordDialogState.getRecord()
        record?.let {
            onConfirm(it)
            onDismissRequest()
        }
    }
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
        Box(
            modifier = Modifier
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
                    text = stringResource(id = R.string.add_record),
                    color = MaterialTheme.colorScheme.primary
                )
                TextField(
                    value = recordDialogState.recordName.value,
                    onValueChange = {
                        recordDialogState.recordName.value = it
                        recordDialogState.nameError.value = false
                    },
                    label = { Text(stringResource(id = R.string.record_name)) },
                    isError = recordDialogState.nameError.value
                )
                TextField(
                    value = recordDialogState.recordCollectedMoney.value,
                    onValueChange = {
                        recordDialogState.recordCollectedMoney.value = it
                        recordDialogState.collectedError.value = false
                    },
                    label = { Text(stringResource(id = R.string.record_collected_money)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = recordDialogState.collectedError.value
                )
                TextField(
                    value = recordDialogState.recordSpentMoney.value,
                    onValueChange = {
                        recordDialogState.recordSpentMoney.value = it
                        recordDialogState.spentError.value = false
                    },
                    label = { Text(stringResource(id = R.string.record_spent_money)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = recordDialogState.spentError.value
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