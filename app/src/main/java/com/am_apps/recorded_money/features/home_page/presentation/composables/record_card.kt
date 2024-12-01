package com.am_apps.recorded_money.features.home_page.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.domain.model.RecordModel

@Composable
fun RecordCard(
    record: RecordModel,
    modifier: Modifier = Modifier,
    onDelete: (record:RecordModel)->Unit = {},
    onUpdate: (record:RecordModel)->Unit = {},
) {
    val style = MaterialTheme.typography.bodyMedium
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = responsiveHeight(20))
            .border(
                1.dp,
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(responsiveSize(20))
            )
            .padding(responsiveSize(10)),
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = record.name,
                    style = style,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(responsiveWidth(5))) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = stringResource(id = R.string.edit_icon_content_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { onUpdate(record) }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = stringResource(id = R.string.delete_icon_content_description),
                        tint = Color.Red,
                        modifier = Modifier.clickable { onDelete(record) }
                    )
                }
            }
/*            Row {
                Text(text = stringResource(R.string.tasks_no), style = style)
                Spacer(modifier = Modifier.padding(end = responsiveHeight(10)))
                Text(text = 0.toString(), style = style)
            }*/
            RecordInOutIcon(collected = record.collectedMoney, spent = record.spentMoney)
        }
    }
}
