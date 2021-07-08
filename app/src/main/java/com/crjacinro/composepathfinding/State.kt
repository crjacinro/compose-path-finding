package com.crjacinro.composepathfinding

import com.crjacinro.composepathfinding.algorithms.startDijkstra
import com.crjacinro.composepathfinding.data.CellData
import com.crjacinro.composepathfinding.data.Position
import com.crjacinro.composepathfinding.ui.composables.CellType
import kotlinx.coroutines.delay

class State {
    private var gridState: MutableList<MutableList<CellData>> = mutableListOf()

    private val startPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4))
    private val finishPosition = Position((NUMBER_OF_ROWS / 2), (NUMBER_OF_COLUMNS / 4) * 3)

    init {
        clear()
    }

    fun clear() {
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

    fun toggleCellTypeToWall(p: Position) {
        if (getCellAtPosition(p).type == CellType.WALL) {
            updateCellTypeAtPosition(p, CellType.BACKGROUND)
        } else {
            updateCellTypeAtPosition(p, CellType.WALL)
        }
    }

    fun isPositionNotAtStartOrFinish(p: Position) =
        getCellAtPosition(p).type != CellType.START &&
                getCellAtPosition(p).type != CellType.FINISH


    private fun updateCellTypeAtPosition(p: Position, cellType: CellType) {
        gridState[p.row][p.column] = getCellAtPosition(p).copy(type = cellType)
    }

    fun setCellVisitedAtPosition(p: Position) {
        gridState[p.row][p.column] = getCellAtPosition(p).copy(isVisited = true)
    }

    suspend fun animatedShortestPath() {
        val shortestPath = startDijkstra(this)
        shortestPath.forEach {
            val p = it.position
            gridState[p.row][p.column] = getCellAtPosition(p).copy(isShortestPath = true)
            delay(GAME_DELAY_IN_MS)
        }
    }

    fun getFinishCell() = getCellAtPosition(finishPosition)

    fun getCellAtPosition(p: Position) = gridState[p.row][p.column]

    private fun addStartAndFinishGrids() {
        gridState[startPosition.row][startPosition.column] =
            CellData(CellType.START, startPosition, distance = 0)
        gridState[finishPosition.row][finishPosition.column] =
            CellData(CellType.FINISH, finishPosition)
    }

    private fun getInitGridState() = getGridWithClearBackground()
}

