package com.am_apps.recorded_money.features.attachments.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import com.am_apps.recorded_money.features.attachments.domain.repo.AttachmentRepo
import java.io.File
import java.io.IOException
import javax.inject.Inject

class AttachmentRepoImpl @Inject constructor(private val context: Context): AttachmentRepo {
    override suspend fun saveToStorage(uri: Uri, recordId: String) {
        val contentResolver = context.contentResolver
        val fileName = contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        } ?: "attachment_${System.currentTimeMillis()}"
        val directory = File(context.filesDir, recordId)
        if (!directory.exists()) {
            directory.mkdir()
        }
        val outputFile = File(directory, fileName)
        try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                outputFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override suspend fun loadFromStorage(recordId: String): List<File> {
        val directory = File(context.filesDir, recordId.toString())
        return if (directory.exists() && directory.isDirectory) {
            directory.listFiles()?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun openAttachment(file: File, context:Context) {
        val fileUri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",  // Use your package name here
            file
        )

        val mimeType = when (file.extension.lowercase()) {
            "pdf" -> "application/pdf"
            "jpg", "jpeg", "png", "gif", "bmp" -> "image/*"
            "mp4", "avi", "mkv" -> "video/*"
            "txt" -> "text/plain"
            else -> "*/*"  // Default to generic MIME type
        }
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(fileUri, mimeType)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}