package com.am_apps.recorded_money.features.attachments.presentation.view_model

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am_apps.recorded_money.features.attachments.domain.model.Attachment
import com.am_apps.recorded_money.features.attachments.domain.repo.AttachmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttachmentViewModel @Inject constructor(
    private val attachmentRepo: AttachmentRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _attachmentState = MutableStateFlow<List<Attachment>>(emptyList())
    val attachmentState = _attachmentState.asStateFlow()
    val recordId:Long? = savedStateHandle["recordId"]
    init {
        recordId?.let {
            loadAttachments(it.toString())
        }
    }
    fun addAttachment(uri: Uri) {
        viewModelScope.launch {
            recordId?.let {
                attachmentRepo.saveToStorage(uri, recordId.toString())
                loadAttachments(recordId.toString())
            }
        }
    }
    private fun loadAttachments(recordId: String) {
        viewModelScope.launch {
            _attachmentState.value = attachmentRepo.loadFromStorage(recordId).map {
                Attachment(it.name, it)
            }
        }
    }
    fun openAttachment(attachment: Attachment, context: Context) {
        viewModelScope.launch {
            attachmentRepo.openAttachment(attachment.file, context)
        }
    }
}
