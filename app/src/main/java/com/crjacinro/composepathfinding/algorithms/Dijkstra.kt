package com.crjacinro.composepathfinding.algorithms

import com.crjacinro.composepathfinding.*
import com.crjacinro.composepathfinding.composables.CellData
import com.crjacinro.composepathfinding.composables.CellType
import kotlinx.coroutines.delay

suspend fun startDijkstra(gridState: State): List<CellData> {
    animatedDijkstra(gridState)
    return getShortestPathOrder(gridState.getFinishCell())
}

private suspend fun animatedDijkstra(gridState: State): List<CellData> {
    val visitedNodesInOrder = mutableListOf<CellData>()
    val unvisitedNodes = gridState.getCurrentGrid().toLinearGrid()

    while (unvisitedNodes.isNotEmpty()) {
        sortNodesByDistance(unvisitedNodes)

        val closestCell = unvisitedNodes.shift()
        if (closestCell.type == CellType.WALL) {
            continue
        }
        if (closestCell.distance == Int.MAX_VALUE) return visitedNodesInOrder

        gridState.setCellVisitedAtPosition(closestCell.position)
        visitedNodesInOrder.add(gridState.getCellAtPosition(closestCell.position))

        if (closestCell.isAtPosition(gridState.getFinishPosition())) return visitedNodesInOrder
        updateUnvisitedNeighbors(closestCell, gridState, unvisitedNodes)

        delay(GAME_DELAY_IN_MS)
    }

    return visitedNodesInOrder
}

private fun getShortestPathOrder(finishedCell: CellData): List<CellData> {
    val nodesInShortestPathOrder = mutableListOf<CellData>()
    var currentCell: CellData? = finishedCell
    while (currentCell != null) {
        nodesInShortestPathOrder.add(0, currentCell)
        currentCell = currentCell.previousShortestCell
    }
    return nodesInShortestPathOrder
}

private fun sortNodesByDistance(unvisitedNodes: MutableList<CellData>) {
    unvisitedNodes.sortBy { it.distance }
}

private fun updateUnvisitedNeighbors(
    cell: CellData,
    gridState: State,
    gridList: MutableList<CellData>
) {
    val unvisitedNeighbors = getUnvisitedNeighbors(cell, gridState)

    for (neighbor in unvisitedNeighbors) {
        val index = gridList.findIndexByCell(neighbor)

        if (index != -1) {
            gridList[index].distance = cell.distance + 1
            gridList[index].previousShortestCell = cell
        }
    }
}

private fun getUnvisitedNeighbors(
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

    return neighbors.filter { !it.isVisited }
}