package com.am_apps.recorded_money.features.home_page.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.presentation.composables.CustomAppBar
import com.am_apps.recorded_money.core.presentation.composables.CustomFab
import com.am_apps.recorded_money.core.presentation.navigation.Screen
import com.am_apps.recorded_money.features.home_page.presentation.composables.RecordDialog
import com.am_apps.recorded_money.features.home_page.presentation.composables.RecordList
import com.am_apps.recorded_money.features.home_page.presentation.composables.TotalMoneyDetails
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.HomePageViewModel
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordDialogState
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordState

@Composable
fun HomePage(navController: NavController, viewModel: HomePageViewModel) {
    val recordsState = viewModel.recordsState.collectAsState()
    val dialogState = viewModel.dialogState.collectAsState()
    val onRecordClicked: (recordId: Long) -> Unit = { recordId ->
        navController.navigate(Screen.Attachment.withArgs(recordId.toString()))
    }
    Scaffold(
        topBar = { CustomAppBar(title = stringResource(id = R.string.home_page_title)) },
        floatingActionButton = { CustomFab(onClick = { viewModel.showAddDialog() }) },
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { padding ->
        if (recordsState.value is RecordState.Success) {
            val state = recordsState.value as RecordState.Success
            val paddingVertical = responsiveHeight(5)
            val paddingHorizontal = responsiveWidth(5)
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(
                        top = paddingVertical,
                        start = paddingHorizontal,
                        end = paddingHorizontal
                    ),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TotalMoneyDetails(state.totalCollectedMoney, state.totalSpentMoney)
                RecordList(
                    records = state.records,
                    onDelete = { record ->
                        viewModel.deleteRecord(record)
                    },
                    onUpdate = { viewModel.showUpdateDialog(it) },
                    onRecordClicked = onRecordClicked
                )
            }
        }
    }
    if (dialogState.value is RecordDialogState.Enable) {
        RecordDialog(
            onConfirm = { record ->
                viewModel.addRecord(record)
            }, onDismissRequest = {
                viewModel.dismissDialog()
            }, recordDialogState = dialogState.value as RecordDialogState.Enable
        )
    }
}