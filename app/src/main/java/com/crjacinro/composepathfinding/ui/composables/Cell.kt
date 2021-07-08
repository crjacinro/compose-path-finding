package com.crjacinro.composepathfinding.ui.composables

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
import com.crjacinro.composepathfinding.data.CellData
import com.crjacinro.composepathfinding.data.Position
import com.crjacinro.composepathfinding.ui.theme.Purple200

@Composable
fun Cell(cellData: CellData, onClick: (Position) -> Unit) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(BorderStroke(1.dp, Color.Gray))
        .height(16.dp)
        .background(getBackgroundByType(cellData))
        .fillMaxWidth()
        .clickable { onClick(cellData.position) }

    Box(modifier = boxModifier)
}

private fun getBackgroundByType(cellData: CellData): Color {
    if (cellData.isShortestPath && cellData.type != CellType.START && cellData.type != CellType.FINISH) return Color.Yellow
    if (cellData.isVisited && cellData.type != CellType.START && cellData.type != CellType.FINISH) return Purple200

    return when (cellData.type) {
        CellType.BACKGROUND -> Color.White
        CellType.WALL -> Color.Black
        CellType.START -> Color.Red
        CellType.FINISH -> Color.Green
    }
}

enum class CellType {
    START,
    FINISH,
    WALL,
    BACKGROUND,
}