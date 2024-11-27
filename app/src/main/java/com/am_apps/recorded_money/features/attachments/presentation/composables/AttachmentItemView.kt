package com.am_apps.recorded_money.features.attachments.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.features.attachments.domain.model.Attachment

@Composable
fun AttachmentItemView(
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit = {},
    attachment: Attachment,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onItemClicked() }
    ) {
        Image(
            painter = painterResource(id = attachment.getImageResource()),
            contentDescription = stringResource(id = R.string.supported_attachments),
            modifier = Modifier
                .fillMaxSize(.75f)
                .padding(bottom = responsiveHeight(5)),
        )
        Text(
            text = attachment.name,
            textAlign = TextAlign.Center,
            maxLines = 3,
            overflow = TextOverflow.Visible,
            modifier = Modifier.fillMaxWidth()
        )
    }
}