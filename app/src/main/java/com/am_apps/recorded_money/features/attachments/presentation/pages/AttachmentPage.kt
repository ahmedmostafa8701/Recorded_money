package com.am_apps.recorded_money.features.attachments.presentation.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveHeight
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.core.presentation.composables.CustomAppBar
import com.am_apps.recorded_money.features.attachments.presentation.composables.AttachmentsView
import com.am_apps.recorded_money.features.attachments.presentation.view_model.AttachmentViewModel

@Composable
fun AttachmentPage(
    viewModel: AttachmentViewModel
) {
    val attachments = viewModel.attachmentState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = { CustomAppBar(title = stringResource(id = R.string.attachment_page_title)) },
        modifier = Modifier.systemBarsPadding().fillMaxSize()
    ) { padding ->
        AttachmentsView(
            modifier = Modifier
                .padding(padding)
                .padding(
                    vertical = responsiveHeight(10),
                    horizontal = responsiveWidth(10)
                ),
            onPicked = { uri -> viewModel.addAttachment(uri) },
            attachments = attachments.value,
            onItemClicked = { attachment -> viewModel.openAttachment(attachment, context) }
        )
    }
}