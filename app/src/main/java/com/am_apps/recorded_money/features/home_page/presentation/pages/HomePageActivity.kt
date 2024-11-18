package com.am_apps.recorded_money.features.home_page.presentation.pages

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.screenSize
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.core.widgets.CustomAppBar
import com.am_apps.recorded_money.core.widgets.CustomFab
import com.am_apps.recorded_money.features.home_page.presentation.composables.AddRecordDialog
import com.am_apps.recorded_money.features.home_page.presentation.composables.RecordList
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.HomePageViewModel
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordState
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecordedMoneyTheme {
                HomePage()
            }
        }
    }
}

@Composable
fun HomePage(viewModel: HomePageViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    screenSize = Pair(configuration.touchscreen, configuration.screenHeightDp)
    val recordsState = viewModel.recordsState.collectAsState()
    var dialogState by remember { mutableStateOf(false) }
    var updateRecord by remember { mutableStateOf(RecordModel(0, "", 0.0, 0.0)) }
    Scaffold(
        topBar = { CustomAppBar(title = stringResource(id = R.string.home_page_title)) },
        floatingActionButton = { CustomFab(onClick = { dialogState = true }) },
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { padding ->
        when (recordsState.value) {
            is RecordState.Loading -> {
                CircularProgressIndicator()
            }

            is RecordState.Success -> {
                val state = recordsState.value as RecordState.Success
                Column(modifier = Modifier.padding(padding).padding(top = 5.dp, start = 5.dp, end = 5.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = stringResource(id = R.string.total_collected))
                        Text(text = state.totalCollectedMoney.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = stringResource(id = R.string.total_spent))
                        Text(text = state.totalSpentMoney.toString())
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = stringResource(id = R.string.rest_money))
                        Text(text = (state.totalCollectedMoney - state.totalSpentMoney).toString())
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(), // Makes it span full width
                        thickness = 1.dp,   // Line thickness
                        color = Color.Gray // Line color
                    )
                    RecordList(
                        records = state.records,
                        onDelete = { record ->
                            viewModel.deleteRecord(record)
                        },
                        onUpdate = { record ->
                            updateRecord = RecordModel(
                                record.id,
                                record.name,
                                record.collectedMoney,
                                record.spentMoney
                            )
                            dialogState = true
                        },
                    )
                }
            }

            is RecordState.Error -> {
                val state = recordsState.value as RecordState.Error
                Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
    if (dialogState) {
        AddRecordDialog(
            onConfirm = { record ->
                viewModel.addRecord(record)
            }, onDismissRequest = {
                dialogState = false
            }, recordForUpdate = updateRecord
        )
    } else {
        updateRecord = RecordModel(0, "", 0.0, 0.0)
    }
}