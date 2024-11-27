package com.am_apps.recorded_money.features.attachments.presentation.composables

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun FilePicker(
    onFilePicked: (Uri) -> Unit
) {
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { onFilePicked(it) }
        }
    )
    filePickerLauncher.launch("*/*") // Allow picking any file type
}