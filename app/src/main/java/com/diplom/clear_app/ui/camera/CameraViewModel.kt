package com.diplom.clear_app.ui.camera

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diplom.clear_app.data.model.ProcessingState
import com.diplom.clear_app.data.repository.InstructionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CameraViewModel(
    private val repository: InstructionRepository = InstructionRepository()
) : ViewModel() {

    private val _processingState = MutableStateFlow<ProcessingState>(ProcessingState.Idle)
    val processingState: StateFlow<ProcessingState> = _processingState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _capturedPhotoUri = MutableStateFlow<Uri?>(null)
    val capturedPhotoUri: StateFlow<Uri?> = _capturedPhotoUri.asStateFlow()

    fun capturePhoto(photoUri: Uri) {
        _capturedPhotoUri.value = photoUri
        processPhoto(photoUri)
    }

    private fun processPhoto(photoUri: Uri) {
        viewModelScope.launch {
            _processingState.value = ProcessingState.Processing
            try {
                repository.processDocument(photoUri).collect { steps ->
                    _processingState.value = ProcessingState.Success(steps)
                }
            } catch (e: Exception) {
                _processingState.value = ProcessingState.Error(
                    "Не удалось обработать фото. Проверьте подключение к интернету и попробуйте снова."
                )
                _errorMessage.value = e.message
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
        _processingState.value = ProcessingState.Idle
    }

    fun resetState() {
        _processingState.value = ProcessingState.Idle
        _capturedPhotoUri.value = null
        _errorMessage.value = null
    }

    fun setCameraError(message: String) {
        _errorMessage.value = message
        _processingState.value = ProcessingState.Error(message)
    }
}
