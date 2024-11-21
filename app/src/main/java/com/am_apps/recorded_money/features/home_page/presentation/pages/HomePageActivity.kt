package com.am_apps.recorded_money.features.home_page.presentation.pages

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.core.widgets.CustomAppBar
import com.am_apps.recorded_money.core.widgets.CustomFab
import com.am_apps.recorded_money.features.home_page.presentation.composables.RecordDialog
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.HomePageViewModel
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordDialogState
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecordedMoneyTheme {
                val viewModel: HomePageViewModel = hiltViewModel()
                val dialogState = viewModel.dialogState.collectAsState()
                Scaffold(
                    topBar = { CustomAppBar(title = stringResource(id = R.string.home_page_title)) },
                    floatingActionButton = { CustomFab(onClick = {viewModel.showAddDialog()}) },
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) { padding ->
                    HomePage(modifier = Modifier.padding(padding), viewModel, onClickUpdate = {
                        viewModel.showUpdateDialog(it)
                    })
                }
                if(dialogState.value is RecordDialogState.Enable){
                    RecordDialog(
                        onConfirm = { record ->
                            viewModel.addRecord(record)
                        }, onDismissRequest = {
                            viewModel.dismissDialog()
                        }, recordDialogState = dialogState.value as RecordDialogState.Enable
                    )
                }
            }
        }
    }
}