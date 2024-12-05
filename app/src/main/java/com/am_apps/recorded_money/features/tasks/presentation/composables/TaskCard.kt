package com.am_apps.recorded_money.features.tasks.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.domain.model.TaskModel
import com.am_apps.recorded_money.core.presentation.composables.CustomIcon
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun TaskCard(
    task: TaskModel,
    modifier: Modifier = Modifier,
    onDelete: (task: TaskModel) -> Unit = {},
    onUpdate: (task: TaskModel) -> Unit = {},
) {
    val style = MaterialTheme.typography.bodyMedium
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                1.dp,
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(responsiveSize(20))
            )
            .padding(responsiveSize(10)),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(responsiveHeight(5))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(responsiveWidth(5)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomIcon(iconId = R.drawable.clock)
                    Row(horizontalArrangement = Arrangement.Absolute.Left){
                        Text(text = task.date, style = style)
                        Spacer(modifier = Modifier.padding(horizontal = responsiveWidth(3)))
                        Text(text = task.time, style = style)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(responsiveWidth(5))) {
                    CustomIcon(iconId = R.drawable.edit, clickable = { onUpdate(task) })
                    CustomIcon(
                        iconId = R.drawable.delete,
                        clickable = { onDelete(task) },
                        color = Color.Red
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(responsiveWidth(5)),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomIcon(iconId = R.drawable.cash)
                Text(text = task.spentMoney.toString(), style = style)
            }
            Text(
                text = task.description,
                style = style,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = responsiveWidth(10)),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun TaskCardPreview() {
    RecordedMoneyTheme {
        TaskCard(
            task = TaskModel(0, "مهمة 1", 20.0, "2022-10-10", "10:30", 1),
            onDelete = {},
            onUpdate = {},
            modifier = Modifier.padding(horizontal = responsiveWidth(20))
        )
    }
}