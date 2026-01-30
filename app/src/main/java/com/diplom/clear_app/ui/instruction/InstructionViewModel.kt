package com.diplom.clear_app.ui.instruction

import androidx.lifecycle.ViewModel
import com.diplom.clear_app.data.model.InstructionStep

class InstructionViewModel : ViewModel() {
    
    private var _steps: List<InstructionStep> = emptyList()
    val steps: List<InstructionStep>
        get() = _steps

    fun setSteps(steps: List<InstructionStep>) {
        _steps = steps
    }

    fun getStepCount(): Int = _steps.size
}
