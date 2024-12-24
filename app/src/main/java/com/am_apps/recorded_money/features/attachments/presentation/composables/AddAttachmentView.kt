package com.am_apps.recorded_money.features.attachments.presentation.composables

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.config.responsiveSize

@Composable
fun AddAttachmentView(modifier: Modifier = Modifier, fileType:String = "*/*", onPicked: (uri: Uri) -> Unit = {}) {
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { onPicked(it) }
        }
    )
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { filePickerLauncher.launch("*/*") },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = stringResource(id = R.string.supported_attachments),
            modifier = Modifier.size(responsiveSize(size = 40)),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
        )
    }
}