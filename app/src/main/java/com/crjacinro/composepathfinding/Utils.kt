package com.crjacinro.composepathfinding

fun getGridWithClearBackground(): MutableList<MutableList<GridType>> {
    val mutableGrid = MutableList(NUMBER_OF_ROWS) {
        MutableList(NUMBER_OF_COLUMNS) { GridType.BACKGROUND }
    }

    for (i in 0 until NUMBER_OF_ROWS) {
        for (j in 0 until NUMBER_OF_COLUMNS) {
            mutableGrid[i][j] = GridType.BACKGROUND
        }
    }

    return mutableGrid
}

fun MutableList<MutableList<GridType>>.addStartAndFinishGrids(): MutableList<MutableList<GridType>> {
    val startPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4))
    val finishPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4) * 3)

    this[startPosition.row][startPosition.column] = GridType.START
    this[finishPosition.row][finishPosition.column] = GridType.FINISH

    return this
}

fun List<List<GridType>>.toLinearGrid(): List<GridType> {
    val mutableList = mutableListOf<GridType>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}