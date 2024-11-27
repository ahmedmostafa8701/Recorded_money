package com.am_apps.recorded_money.core.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveSize

@Composable
fun CustomFab(modifier: Modifier = Modifier, painter: Painter = painterResource(id = R.drawable.add), onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier.size(responsiveSize(65))
    ) {
        Icon(
            painter = painter,
            contentDescription = stringResource(id = R.string.add_record_content_desc),
        )
    }
}