package com.crjacinro.composepathfinding.algorithms

import com.crjacinro.composepathfinding.*

fun dijkstra(
    gridState: State,
    start: Position,
    finish: Position
): List<CellData> {
    gridState.updateCellDistanceAtPosition(start, 0)

    val visitedNodesInOrder = mutableListOf<CellData>()
    val unvisitedNodes = gridState.getCurrentGrid().toLinearGrid().toMutableList()

    while (unvisitedNodes.isNotEmpty()) {
        sortNodesByDistance(unvisitedNodes)

        val closestCell = unvisitedNodes.shift()
        if (closestCell.type == CellType.WALL) continue
        if (closestCell.distance == Int.MAX_VALUE) return visitedNodesInOrder

        gridState.updateCellTypeAtPosition(closestCell.position, CellType.VISITED)
        visitedNodesInOrder.add(closestCell)

        if (closestCell.isAtPosition(finish)) return visitedNodesInOrder
        updateUnvisitedNeighbors(closestCell, gridState)
    }

    return visitedNodesInOrder
}

fun MutableList<CellData>.shift(): CellData {
    val first = this.first()
    this.removeAt(0)
    return first
}

fun CellData.isAtPosition(position: Position) =
    this.position.row == position.row && this.position.column == position.column


fun sortNodesByDistance(unvisitedNodes: MutableList<CellData>) {
    unvisitedNodes.sortBy { it.distance }
}

fun updateUnvisitedNeighbors(cell: CellData, gridState: State) {
    val unvisitedNeighbors = getUnvisitedNeighbors(cell, gridState)

    for (neighbor in unvisitedNeighbors) {
        gridState.updateCellDistanceAtPosition(neighbor.position, neighbor.distance + 1)
        gridState.updatePreviousShortestCellAtPosition(neighbor.position, neighbor)
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