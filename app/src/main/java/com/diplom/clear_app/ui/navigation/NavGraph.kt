package com.diplom.clear_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diplom.clear_app.data.model.InstructionStep
import com.diplom.clear_app.ui.camera.CameraScreen
import com.diplom.clear_app.ui.instruction.InstructionScreen

sealed class Screen {
    abstract val route: String
    
    object Camera : Screen() {
        override val route = "camera"
    }
    
    object Instruction : Screen() {
        override val route = "instruction"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Camera.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Camera.route) {
            CameraScreen(
                onNavigateToInstruction = { steps ->
                    navController.navigate("instruction") {
                        // Сохраняем шаги в NavController для передачи
                        // В реальном приложении лучше использовать SavedStateHandle или ViewModel
                    }
                    // Сохраняем шаги глобально для доступа на следующем экране
                    InstructionStepsHolder.steps = steps
                }
            )
        }

        composable(Screen.Instruction.route) {
            val steps = InstructionStepsHolder.steps
            if (steps != null) {
                InstructionScreen(
                    steps = steps,
                    onNewPhotoClick = {
                        InstructionStepsHolder.steps = null
                        navController.navigate(Screen.Camera.route) {
                            popUpTo(Screen.Camera.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            } else {
                // Если шаги не найдены, возвращаемся на камеру
                navController.navigate(Screen.Camera.route) {
                    popUpTo(Screen.Camera.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

// Временное хранилище для передачи данных между экранами
// В реальном приложении лучше использовать SavedStateHandle или ViewModel
object InstructionStepsHolder {
    var steps: List<InstructionStep>? = null
}
