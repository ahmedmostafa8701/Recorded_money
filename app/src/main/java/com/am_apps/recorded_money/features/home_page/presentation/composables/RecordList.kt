package com.am_apps.recorded_money.features.home_page.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.core.domain.model.RecordModel

@Composable
fun RecordList(
    modifier: Modifier = Modifier,
    records: List<RecordModel>,
    onDelete: (record: RecordModel) -> Unit,
    onUpdate: (record: RecordModel) -> Unit,
    onRecordClicked: (recordId: Long) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(records.size) {
            RecordCard(
                record = records[it],
                onDelete = { record ->
                    onDelete(record)
                },
                onUpdate = { record ->
                    onUpdate(record)
                },
                modifier = Modifier.clickable {
                    onRecordClicked(records[it].id)
                }
            )
        }
    }
}