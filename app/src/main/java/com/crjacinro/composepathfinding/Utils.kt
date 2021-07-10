package com.crjacinro.composepathfinding

import com.crjacinro.composepathfinding.data.CellData
import com.crjacinro.composepathfinding.data.Position
import com.crjacinro.composepathfinding.ui.composables.CellType

fun getGridWithClearBackground(): MutableList<MutableList<CellData>> {
    val mutableGrid = MutableList(NUMBER_OF_ROWS) {
        MutableList(NUMBER_OF_COLUMNS) {
            CellData(CellType.BACKGROUND, Position(0, 0))
        }
    }

    for (i in 0 until NUMBER_OF_ROWS) {
        for (j in 0 until NUMBER_OF_COLUMNS) {
            mutableGrid[i][j] = CellData(CellType.BACKGROUND, Position(i, j))
        }
    }

    return mutableGrid
}

fun List<List<CellData>>.toLinearGrid(): MutableList<CellData> {
    val mutableList = mutableListOf<CellData>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList
}

fun MutableList<CellData>.shift(): CellData {
    val first = this.first()
    this.removeAt(0)
    return first
}

fun CellData.isAtPosition(position: Position) =
    this.position.row == position.row && this.position.column == position.column

fun MutableList<CellData>.findIndexByCell(cell: CellData): Int {
    for (i in 0 until this.size) {
        if (this[i].id == cell.id) {
            return i
        }
    }
    return -1
}

fun weightedRandomWall(): CellType {
    val random = (0..100).random()
    return if (random in 0..70) CellType.BACKGROUND else CellType.WALL
}
