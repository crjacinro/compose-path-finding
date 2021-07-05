package com.crjacinro.composepathfinding.algorithms

import com.crjacinro.composepathfinding.CellData
import com.crjacinro.composepathfinding.CellType
import com.crjacinro.composepathfinding.Position
import com.crjacinro.composepathfinding.toLinearGrid

fun dijkstra(
    grid: MutableList<MutableList<CellData>>,
    start: Position,
    finish: Position
): List<CellData> {
    grid[start.row][start.column].distance = 0

    val visitedNodesInOrder = mutableListOf<CellData>()
    val unvisitedNodes = grid.toLinearGrid().toMutableList()

    while (unvisitedNodes.isNotEmpty()) {
        sortNodesByDistance(unvisitedNodes)

        val closestCell = unvisitedNodes.shift()
        if (closestCell.type == CellType.WALL) continue
        if (closestCell.distance == Int.MAX_VALUE) return visitedNodesInOrder

        grid[closestCell.position.row][closestCell.position.column].type = CellType.VISITED
        visitedNodesInOrder.add(closestCell)

        if (closestCell.isAtPosition(finish)) return visitedNodesInOrder
        updateUnvisitedNeighbors(closestCell, grid)
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

fun updateUnvisitedNeighbors(cell: CellData, grid: MutableList<MutableList<CellData>>) {
    val unvisitedNeighbors = getUnvisitedNeighbors(cell, grid)

    for (neighbor in unvisitedNeighbors) {
        grid[neighbor.position.row][neighbor.position.column].distance = neighbor.distance + 1
        grid[neighbor.position.row][neighbor.position.column].previousShortestCell = neighbor
    }
}

fun getUnvisitedNeighbors(
    cell: CellData,
    grid: MutableList<MutableList<CellData>>
): List<CellData> {
    val neighbors = mutableListOf<CellData>()
    val (row, column) = cell.position

    if (row > 0) neighbors.add(grid[row - 1][column])
    if (row < grid.size - 1) neighbors.add(grid[row + 1][column])
    if (column > 0) neighbors.add(grid[row][column - 1])
    if (column < grid[0].size - 1) neighbors.add(grid[row][column + 1])

    return neighbors.filter { it.type != CellType.VISITED }
}