package com.am_apps.recorded_money.features.attachments.domain.repo

import android.content.Context
import android.net.Uri
import java.io.File

interface AttachmentRepo {
    suspend fun saveToStorage(
        uri:Uri,
        recordId: String
    )
    suspend fun loadFromStorage(
        recordId: String
    ): List<File>
    suspend fun openAttachment(
        file: File,
        context: Context
    )
}