package com.crjacinro.composepathfinding.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.crjacinro.composepathfinding.NUMBER_OF_COLUMNS
import com.crjacinro.composepathfinding.data.CellData
import com.crjacinro.composepathfinding.data.Position

@ExperimentalFoundationApi
@Composable
fun PathFindingGrid(
    cellData: List<CellData>,
    onClick: (Position) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(NUMBER_OF_COLUMNS),
        modifier = Modifier
            .padding(4.dp)
            .border(BorderStroke(6.dp, Color.Black))
    ) {
        items(cellData) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Cell(it, onClick)
            }
        }
    }
}