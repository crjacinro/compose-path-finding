package com.crjacinro.composepathfinding

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

fun MutableList<MutableList<CellData>>.addStartAndFinishGrids(): MutableList<MutableList<CellData>> {
    val startPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4))
    val finishPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4) * 3)

    this[startPosition.row][startPosition.column] = CellData(CellType.START, startPosition)
    this[finishPosition.row][finishPosition.column] = CellData(CellType.FINISH, finishPosition)

    return this
}

fun List<List<CellData>>.toLinearGrid(): List<CellData> {
    val mutableList = mutableListOf<CellData>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}