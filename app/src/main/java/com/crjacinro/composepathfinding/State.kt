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

    fun getStartPosition() = startPosition
    fun getFinishPosition() = finishPosition

    fun getCurrentGrid(): List<List<CellData>> = gridState

    fun updateCellTypeAtPosition(p: Position, cellType: CellType) {
        gridState[p.row][p.column] = gridState[p.row][p.column].copy(type = cellType, position = p)
    }

    fun updateCellDistanceAtPosition(p: Position, distance: Int) {
        gridState[p.row][p.column] =
            gridState[p.row][p.column].copy(distance = distance, position = p)
    }

    fun updatePreviousShortestCellAtPosition(p: Position, cell: CellData) {
        gridState[p.row][p.column] =
            gridState[p.row][p.column].copy(previousShortestCell = cell, position = p)
    }

    private fun addStartAndFinishGrids() {
        gridState[startPosition.row][startPosition.column] = CellData(CellType.START, startPosition)
        gridState[finishPosition.row][finishPosition.column] =
            CellData(CellType.FINISH, finishPosition)
    }

    private fun getInitGridState() = getGridWithClearBackground()
}

