package com.crjacinro.composepathfinding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Grid(gridType: GridType) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(BorderStroke(1.dp, Color.Gray))
        .height(16.dp)
        .background(getBackgroundByType(gridType))
        .fillMaxWidth()

    Box(modifier = boxModifier) {
        if (gridType == GridType.START) {
            Image(painter = painterResource(R.drawable.ic_start), contentDescription = "")
        } else if (gridType == GridType.FINISH) {
            Image(painter = painterResource(R.drawable.ic_finish), contentDescription = "")
        }
    }
}

private fun getBackgroundByType(gridType: GridType) =
    when (gridType) {
        GridType.BACKGROUND -> Color.White
        else -> Color.White
    }


enum class GridType {
    START,
    FINISH,
    WALL,
    BACKGROUND,
    VISITED,
}