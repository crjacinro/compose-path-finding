package com.crjacinro.composepathfinding.algorithms

import com.crjacinro.composepathfinding.*
import kotlinx.coroutines.delay

suspend fun dijkstra(gridState: State): List<CellData> {
    val visitedNodesInOrder = mutableListOf<CellData>()
    val unvisitedNodes = gridState.getCurrentGrid().toLinearGrid()

    while (unvisitedNodes.isNotEmpty()) {
        sortNodesByDistance(unvisitedNodes)

        val closestCell = unvisitedNodes.shift()
        if (closestCell.type == CellType.WALL) continue
        if (closestCell.distance == Int.MAX_VALUE) return visitedNodesInOrder

        gridState.updateCellTypeAtPosition(closestCell.position, CellType.VISITED)
        visitedNodesInOrder.add(closestCell)

        if (closestCell.isAtPosition(gridState.getFinishPosition())) return visitedNodesInOrder
        updateUnvisitedNeighbors(closestCell, gridState, unvisitedNodes)

        delay(GAME_DELAY_IN_MS)
    }

    return visitedNodesInOrder
}

fun sortNodesByDistance(unvisitedNodes: MutableList<CellData>) {
    unvisitedNodes.sortBy { it.distance }
}

fun updateUnvisitedNeighbors(cell: CellData, gridState: State, gridList: MutableList<CellData>) {
    val unvisitedNeighbors = getUnvisitedNeighbors(cell, gridState)

    for (neighbor in unvisitedNeighbors) {
        val index = gridList.findIndexByCell(neighbor)

        gridList[index].distance = cell.distance + 1
        gridList[index].previousShortestCell = cell
    }
}


fun getUnvisitedNeighbors(
    cell: CellData,
    gridState: State
): List<CellData> {
    val neighbors = mutableListOf<CellData>()
    val grid = gridState.getCurrentGrid()
    val (row, column) = cell.position

    if (row > 0) neighbors.add(grid[row - 1][column])
    if (row < grid.size - 1) neighbors.add(grid[row + 1][column])
    if (column > 0) neighbors.add(grid[row][column - 1])
    if (column < grid[0].size - 1) neighbors.add(grid[row][column + 1])

    return neighbors.filter { it.type != CellType.VISITED }
}