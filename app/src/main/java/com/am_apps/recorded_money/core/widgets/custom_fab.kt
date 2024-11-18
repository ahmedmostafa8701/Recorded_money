package com.am_apps.recorded_money.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveSize

@Composable
fun CustomFab(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier.size(responsiveSize(65))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = stringResource(id = R.string.add_record_content_desc),
        )
    }
}