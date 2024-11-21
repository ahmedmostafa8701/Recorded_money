package com.am_apps.recorded_money.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveFont
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.switchLanguage

@Composable
fun CustomAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {}
) {
    val languagePainter = painterResource(id = R.drawable.language)
    var context = LocalContext.current
    Row(
        modifier = modifier
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.primary)
            .height(responsiveHeight(54))
            .fillMaxWidth()
            .padding(horizontal = responsiveWidth(10)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = responsiveFont(24)),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row {
            Icon(
                painter = languagePainter,
                contentDescription = stringResource(id = R.string.language_icon),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable { switchLanguage(context) }
            )
            actions()
        }
    }
}