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
fun Cell(cellData: CellData, onClick: (Position) -> Unit) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(BorderStroke(1.dp, Color.Gray))
        .height(16.dp)
        .background(getBackgroundByType(cellData.type))
        .fillMaxWidth()
        .clickable { onClick(cellData.position) }

    Box(modifier = boxModifier)
}

private fun getBackgroundByType(cellType: CellType) =
    when (cellType) {
        CellType.BACKGROUND -> Color.White
        CellType.WALL -> Color.Black
        CellType.VISITED -> Color.Blue
        CellType.START -> Color.Red
        CellType.FINISH -> Color.Green
    }

enum class CellType {
    START,
    FINISH,
    WALL,
    BACKGROUND,
    VISITED,
}

data class CellData(
    var type: CellType,
    val position: Position,
    var distance: Int = Int.MAX_VALUE,
    var previousShortestCell: CellData? = null
)