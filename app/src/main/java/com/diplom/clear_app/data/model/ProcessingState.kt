package com.diplom.clear_app.data.model

sealed class ProcessingState {
    object Idle : ProcessingState()
    object Processing : ProcessingState()
    data class Success(val steps: List<InstructionStep>) : ProcessingState()
    data class Error(val message: String) : ProcessingState()
}
