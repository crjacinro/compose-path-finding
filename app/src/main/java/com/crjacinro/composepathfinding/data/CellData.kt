package com.crjacinro.composepathfinding.data

import com.crjacinro.composepathfinding.ui.composables.CellType

data class CellData(
    var type: CellType,
    val position: Position,
    val isVisited: Boolean = false,
    val isShortestPath: Boolean = false,
    var distance: Int = Int.MAX_VALUE,
    var previousShortestCell: CellData? = null,
    var id: Int = (0..Int.MAX_VALUE).random()
)