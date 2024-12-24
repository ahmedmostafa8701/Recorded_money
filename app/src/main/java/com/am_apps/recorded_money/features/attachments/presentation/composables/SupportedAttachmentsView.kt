package com.am_apps.recorded_money.features.attachments.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.config.responsiveFont
import com.am_apps.recorded_money.config.responsiveHeight
import com.am_apps.recorded_money.config.responsiveSize
import com.am_apps.recorded_money.config.responsiveWidth

@Composable
fun SupportedAttachmentsView(modifier: Modifier = Modifier) {
    var imagesId = listOf(
        R.drawable.docx,
        R.drawable.pdf,
        R.drawable.excel,
        R.drawable.txt,
        R.drawable.picture
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                vertical = responsiveHeight(
                    height = 5
                )
            )
    ) {
        Text(
            text = stringResource(id = R.string.supported_attachments),
            fontSize = responsiveFont(18),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Row(
            modifier = modifier
                .padding(horizontal = responsiveWidth(width = 10)),
            horizontalArrangement = Arrangement.Center
        ) {
            imagesId.map {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = stringResource(id = R.string.supported_attachments),
                    modifier = Modifier
                        .padding(horizontal = responsiveWidth(width = 5))
                        .size(responsiveSize(size = 20)),
                )
            }
        }
    }
}