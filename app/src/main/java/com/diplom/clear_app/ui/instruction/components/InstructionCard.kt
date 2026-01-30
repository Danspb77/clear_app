package com.diplom.clear_app.ui.instruction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diplom.clear_app.data.model.InstructionStep
import com.diplom.clear_app.ui.theme.AccentGreen
import com.diplom.clear_app.ui.theme.AccentOrange
import com.diplom.clear_app.ui.theme.AccentPurple
import com.diplom.clear_app.ui.theme.AccentRed
import com.diplom.clear_app.ui.theme.PrimaryBlue

@Composable
fun InstructionCard(
    step: InstructionStep,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Номер шага в цветном круге
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(getStepColor(step.stepNumber)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step.stepNumber.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Иконка и текст
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = getStepIcon(step.stepNumber),
                        contentDescription = step.title,
                        tint = getStepColor(step.stepNumber),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = step.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = step.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun getStepColor(stepNumber: Int): Color {
    return when (stepNumber % 5) {
        1 -> PrimaryBlue
        2 -> AccentRed
        3 -> AccentGreen
        4 -> AccentOrange
        else -> AccentPurple
    }
}

@Composable
private fun getStepIcon(stepNumber: Int): ImageVector {
    return when (stepNumber % 5) {
        1 -> Icons.Default.Person
        2 -> Icons.Default.Call
        3 -> Icons.Default.Email
        4 -> Icons.Default.Check
        else -> Icons.Default.Info
    }
}
