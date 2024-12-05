package com.am_apps.recorded_money.core.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.am_apps.recorded_money.confg.responsiveSize

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    iconId: Int,
    contentDescription: String = iconId.toString(),
    color: Color = MaterialTheme.colorScheme.primary,
    clickable: () -> Unit = {},
    size: Double = 20.0
){
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = contentDescription,
        tint = color,
        modifier = modifier.size(responsiveSize(size)).clickable { clickable() }
    )
}