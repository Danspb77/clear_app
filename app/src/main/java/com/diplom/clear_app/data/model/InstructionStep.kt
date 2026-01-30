package com.diplom.clear_app.data.model

import androidx.annotation.DrawableRes

data class InstructionStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    @DrawableRes val iconResId: Int,
    val colorResId: Int
)
