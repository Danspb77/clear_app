package com.diplom.clear_app.data.repository

import android.net.Uri
import com.diplom.clear_app.data.model.InstructionStep
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InstructionRepository {
    
    fun processDocument(photoUri: Uri): Flow<List<InstructionStep>> = flow {
        delay(2500) // Симуляция обработки документа
        
        val mockSteps = listOf(
            InstructionStep(
                stepNumber = 1,
                title = "Примите лекарство",
                description = "Примите одну таблетку утром после завтрака, запивая стаканом воды.",
                iconResId = android.R.drawable.ic_menu_view, // Временно, потом заменим на кастомные иконки
                colorResId = android.R.color.holo_blue_dark
            ),
            InstructionStep(
                stepNumber = 2,
                title = "Измерьте давление",
                description = "Измерьте артериальное давление через 30 минут после приёма лекарства.",
                iconResId = android.R.drawable.ic_menu_view,
                colorResId = android.R.color.holo_red_dark
            ),
            InstructionStep(
                stepNumber = 3,
                title = "Запишите результат",
                description = "Запишите показания давления в дневник или приложение для отслеживания.",
                iconResId = android.R.drawable.ic_menu_view,
                colorResId = android.R.color.holo_green_dark
            ),
            InstructionStep(
                stepNumber = 4,
                title = "Повторите вечером",
                description = "Вечером перед сном повторите приём лекарства по той же схеме.",
                iconResId = android.R.drawable.ic_menu_view,
                colorResId = android.R.color.holo_orange_dark
            ),
            InstructionStep(
                stepNumber = 5,
                title = "Проконсультируйтесь с врачом",
                description = "Если давление не нормализуется в течение недели, обратитесь к врачу.",
                iconResId = android.R.drawable.ic_menu_view,
                colorResId = android.R.color.holo_purple
            )
        )
        
        emit(mockSteps)
    }
}
