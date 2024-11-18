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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.am_apps.recorded_money.validateOnlyDoubles

@Composable
fun AddRecordDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (record: RecordModel) -> Unit,
    recordForUpdate: RecordModel = RecordModel(0, "", 0.0, 0.0)
) {
    var recordName by remember { mutableStateOf(recordForUpdate.name) }
    var recordCollectedMoney by remember { mutableStateOf(recordForUpdate.collectedMoney.toString()) }
    var recordSpentMoney by remember { mutableStateOf(recordForUpdate.spentMoney.toString()) }
    var nameError by remember { mutableStateOf(false) }
    var collectedError by remember { mutableStateOf(false) }
    var spentError by remember { mutableStateOf(false) }
    val addRecord = {
        nameError = recordName.isEmpty()
        if (!nameError && !collectedError && !spentError) {
            val record = RecordModel(
                recordForUpdate.id,
                recordName,
                recordCollectedMoney.toDouble(),
                recordSpentMoney.toDouble()
            )
            onConfirm(record)
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
                    value = recordName,
                    onValueChange = {
                        recordName = it
                        nameError = recordName.isEmpty()
                    },
                    label = { Text(stringResource(id = R.string.record_name)) },
                    isError = nameError
                )
                TextField(
                    value = recordCollectedMoney,
                    onValueChange = { newValue ->
                        collectedError = !validateOnlyDoubles(newValue)
                        recordCollectedMoney = newValue
                    },
                    label = { Text(stringResource(id = R.string.record_collected_money)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = collectedError
                )
                TextField(
                    value = recordSpentMoney,
                    onValueChange = { newValue ->
                        spentError = !validateOnlyDoubles(newValue)
                        recordSpentMoney = newValue
                    },
                    label = { Text(stringResource(id = R.string.record_spent_money)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = spentError
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
                        Text(text = stringResource(id = R.string.add))
                    }

                }
            }
        }
    }
}
