package com.am_apps.recorded_money.features.home_page.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.config.responsiveHeight
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun TotalMoneyDetails(totalCollectedMoney:Double, totalSpentMoney:Double) {
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(text = stringResource(id = R.string.total_collected))
            Text(text = totalCollectedMoney.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(text = stringResource(id = R.string.total_spent))
            Text(text = totalSpentMoney.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(text = stringResource(id = R.string.rest_money))
            Text(text = (totalCollectedMoney - totalSpentMoney).toString())
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = responsiveHeight(height = 5)), // Makes it span full width
            thickness = 1.dp,   // Line thickness
            color = Color.Gray, // Line color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TotalMoneyDetailsPreview() {
    RecordedMoneyTheme {
        TotalMoneyDetails(100.0, 50.0)
    }
}