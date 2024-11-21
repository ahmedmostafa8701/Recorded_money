package com.am_apps.recorded_money.features.home_page.presentation.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.domain.model.RecordModel
import com.am_apps.recorded_money.features.home_page.presentation.composables.RecordList
import com.am_apps.recorded_money.features.home_page.presentation.composables.TotalMoneyDetails
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.HomePageViewModel
import com.am_apps.recorded_money.features.home_page.presentation.viewmodel.RecordState

@Composable
fun HomePage(modifier: Modifier = Modifier, viewModel: HomePageViewModel, onClickUpdate: (RecordModel)->Unit) {
    val recordsState = viewModel.recordsState.collectAsState()
    when (recordsState.value) {
        is RecordState.Loading -> {
            CircularProgressIndicator()
        }

        is RecordState.Success -> {
            val state = recordsState.value as RecordState.Success
            val paddingVertical = responsiveHeight(5)
            val paddingHorizontal = responsiveWidth(5)
            Column(
                modifier = modifier.padding(top = paddingVertical, start = paddingHorizontal, end = paddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TotalMoneyDetails(state.totalCollectedMoney, state.totalSpentMoney)
                RecordList(
                    records = state.records,
                    onDelete = { record ->
                        viewModel.deleteRecord(record)
                    },
                    onUpdate = onClickUpdate,
                )
            }
        }
        is RecordState.Error -> {
            val state = recordsState.value as RecordState.Error
            Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            //Initial state
        }
    }
}