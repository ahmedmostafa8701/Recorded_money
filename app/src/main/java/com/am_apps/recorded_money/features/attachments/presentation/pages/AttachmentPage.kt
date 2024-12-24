package com.am_apps.recorded_money.features.attachments.presentation.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import com.am_apps.recorded_money.MyApp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.config.responsiveHeight
import com.am_apps.recorded_money.config.responsiveWidth
import com.am_apps.recorded_money.core.presentation.composables.CustomAppBar
import com.am_apps.recorded_money.features.attachments.data.AttachmentRepoImpl
import com.am_apps.recorded_money.features.attachments.presentation.composables.AttachmentsView
import com.am_apps.recorded_money.features.attachments.presentation.view_model.AttachmentViewModel
import com.am_apps.recorded_money.ui.theme.RecordedMoneyTheme

@Composable
fun AttachmentPage(
    viewModel: AttachmentViewModel
) {
    val attachments = viewModel.attachmentState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = { CustomAppBar(title = stringResource(id = R.string.attachment_page_title)) },
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
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
            onItemClicked = { attachment -> viewModel.openAttachment(attachment, context) },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AttachmentPagePreview() {
    val repo = AttachmentRepoImpl(MyApp.instance.applicationContext)
    val viewModel = AttachmentViewModel(repo, SavedStateHandle())
    RecordedMoneyTheme {
        Scaffold { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AttachmentPage(viewModel)
            }
        }
    }
}