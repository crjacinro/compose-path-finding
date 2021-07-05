package com.crjacinro.composepathfinding

class State {
    private var gridState: MutableList<MutableList<CellData>> = getInitGridState()

    fun drawCurrentGridState(): List<List<CellData>> {
        val updatedGrid = getInitGridState()

        for (i in 0 until updatedGrid.size) {
            for (j in 0 until updatedGrid[i].size) {
                updatedGrid[i][j] = gridState[i][j]
            }
        }

        return updatedGrid
    }

    fun updateCellAtPosition(p: Position, cellType: CellType) {
        gridState[p.row][p.column] = gridState[p.row][p.column].copy(type = cellType, position = p)
    }
}

fun getInitGridState() = getGridWithClearBackground()
    .addStartAndFinishGrids()

