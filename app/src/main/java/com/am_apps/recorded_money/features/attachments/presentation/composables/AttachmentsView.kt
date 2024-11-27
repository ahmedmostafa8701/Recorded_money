package com.am_apps.recorded_money.features.attachments.presentation.composables

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.features.attachments.domain.model.Attachment

@Composable
fun AttachmentsView(
    modifier: Modifier = Modifier,
    attachments: List<Attachment>,
    onPicked: (uri: Uri) -> Unit = { },
    onItemClicked: (attachment: Attachment) -> Unit = { }
) {
    val size = responsiveSize(size = 90)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = size),
        verticalArrangement = Arrangement.spacedBy(responsiveHeight(10)),
        horizontalArrangement = Arrangement.spacedBy(responsiveWidth(10)),
        modifier = modifier
    ) {
        item {
            AddAttachmentView(
                modifier = Modifier.size(size),
                onPicked = onPicked
            )
        }
        items(attachments) { attachment ->
            AttachmentItemView(
                onItemClicked = {
                    onItemClicked(attachment)
                },
                attachment = attachment,
                modifier = Modifier.size(size)
            )
        }
    }
}