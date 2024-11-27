package com.am_apps.recorded_money.features.attachments.domain.model

import com.am_apps.recorded_money.R
import java.io.File

data class Attachment(
    val name: String,
    val file:File
){
    fun getImageResource():Int{
        return when(file.extension.lowercase()){
            "pdf" -> R.drawable.pdf
            "txt" -> R.drawable.txt
            "docx" -> R.drawable.docx
            "xls" -> R.drawable.excel
            "jpg", "jpeg", "png", "gif", "bmp" -> R.drawable.picture
            else -> R.drawable.document
        }
    }
}