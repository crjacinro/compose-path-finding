package com.crjacinro.composepathfinding

class State {
    private var gridState: MutableList<MutableList<GridData>> = getInitGridState()

    fun drawCurrentGridState(): List<List<GridData>> {
        val updatedGrid = getInitGridState()

        for (i in 0 until updatedGrid.size) {
            for (j in 0 until updatedGrid[i].size) {
                updatedGrid[i][j] = gridState[i][j]
            }
        }

        return updatedGrid
    }

    fun updateGridTypeAtPosition(p: Position, gridType: GridType) {
        gridState[p.row][p.column] = gridState[p.row][p.column].copy(type = gridType, position = p)
    }
}

fun getInitGridState() = getGridWithClearBackground()
    .addStartAndFinishGrids()

