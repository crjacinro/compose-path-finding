package com.crjacinro.composepathfinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.crjacinro.composepathfinding.algorithms.startDijkstra
import com.crjacinro.composepathfinding.composables.CellData
import com.crjacinro.composepathfinding.composables.CellType
import com.crjacinro.composepathfinding.composables.PathFindingGrid
import com.crjacinro.composepathfinding.ui.theme.ComposePathFindingTheme
import kotlinx.coroutines.*

private val state = State()
private val scope = CoroutineScope(Dispatchers.Default)

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePathFindingTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PathFindingApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.cancel()
    }
}

@ExperimentalFoundationApi
@Composable
fun PathFindingApp() {
    val currentGridState = remember { mutableStateOf(state.drawCurrentGridState()) }

    val onCellClicked = { p: Position ->
        state.updateCellTypeAtPosition(p, CellType.WALL)
    }

    PathFindingUi(currentGridState.value, onCellClicked)
    LaunchedEffect(Unit) {
        while (true) {
            delay(GAME_DELAY_IN_MS)
            currentGridState.value = state.drawCurrentGridState()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PathFindingUi(cell: List<List<CellData>>, onClick: (Position) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PathFindingGrid(cell.toLinearGrid(), onClick)
        Button(onClick = {
            scope.launch {
                val shortestPath = startDijkstra(state)

                state.animatedShortestPath(shortestPath)
            }
        }) {
            Text("Visualize")
        }
    }
}