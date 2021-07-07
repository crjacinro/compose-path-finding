package com.crjacinro.composepathfinding

class State {
    private var gridState: MutableList<MutableList<CellData>>

    private val startPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4))
    private val finishPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4) * 3)

    init {
        gridState = getInitGridState()
        addStartAndFinishGrids()
    }

    fun drawCurrentGridState(): List<List<CellData>> {
        val updatedGrid = getInitGridState()

        for (i in 0 until updatedGrid.size) {
            for (j in 0 until updatedGrid[i].size) {
                updatedGrid[i][j] = gridState[i][j]
            }
        }

        return updatedGrid
    }

    fun getFinishPosition() = finishPosition

    fun getCurrentGrid(): List<List<CellData>> = gridState

    fun updateCellTypeAtPosition(p: Position, cellType: CellType) {
        gridState[p.row][p.column] = gridState[p.row][p.column].copy(type = cellType, position = p)
    }

    fun setCellVisitedAtPosition(p: Position) {
        gridState[p.row][p.column] = gridState[p.row][p.column].copy(isVisited = true, position = p)
    }

    private fun addStartAndFinishGrids() {
        gridState[startPosition.row][startPosition.column] =
            CellData(CellType.START, startPosition, distance = 0)
        gridState[finishPosition.row][finishPosition.column] =
            CellData(CellType.FINISH, finishPosition)
    }

    private fun getInitGridState() = getGridWithClearBackground()
}

