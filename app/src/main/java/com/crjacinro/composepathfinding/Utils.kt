package com.crjacinro.composepathfinding

fun getGridWithClearBackground(): MutableList<MutableList<GridData>> {
    val mutableGrid = MutableList(NUMBER_OF_ROWS) {
        MutableList(NUMBER_OF_COLUMNS) {
            GridData(GridType.BACKGROUND, Position(0, 0))
        }
    }

    for (i in 0 until NUMBER_OF_ROWS) {
        for (j in 0 until NUMBER_OF_COLUMNS) {
            mutableGrid[i][j] = GridData(GridType.BACKGROUND, Position(i, j))
        }
    }

    return mutableGrid
}

fun MutableList<MutableList<GridData>>.addStartAndFinishGrids(): MutableList<MutableList<GridData>> {
    val startPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4))
    val finishPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4) * 3)

    this[startPosition.row][startPosition.column] = GridData(GridType.START, startPosition)
    this[finishPosition.row][finishPosition.column] = GridData(GridType.FINISH, finishPosition)

    return this
}

fun List<List<GridData>>.toLinearGrid(): List<GridData> {
    val mutableList = mutableListOf<GridData>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}