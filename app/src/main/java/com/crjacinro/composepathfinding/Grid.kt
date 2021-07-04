package com.crjacinro.composepathfinding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Grid(gridData: GridData, onClick: (Position) -> Unit) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(BorderStroke(1.dp, Color.Gray))
        .height(16.dp)
        .background(getBackgroundByType(gridData.type))
        .fillMaxWidth()
        .clickable { onClick(gridData.position) }

    Box(modifier = boxModifier)
}

private fun getBackgroundByType(gridType: GridType) =
    when (gridType) {
        GridType.BACKGROUND -> Color.White
        GridType.WALL -> Color.Black
        GridType.VISITED -> Color.Yellow
        GridType.START -> Color.Red
        GridType.FINISH -> Color.Green
    }


enum class GridType {
    START,
    FINISH,
    WALL,
    BACKGROUND,
    VISITED,
}

data class GridData(
    val type: GridType,
    val position: Position
)