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

fun List<List<GridType>>.toLinearGrid(): List<GridType> {
    val mutableList = mutableListOf<GridType>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}