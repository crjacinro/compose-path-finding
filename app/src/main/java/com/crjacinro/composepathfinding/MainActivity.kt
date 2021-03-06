package com.crjacinro.composepathfinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crjacinro.composepathfinding.data.CellData
import com.crjacinro.composepathfinding.data.Position
import com.crjacinro.composepathfinding.ui.composables.*
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
        if (state.isPositionNotAtStartOrFinish(p) && !state.isVisualizing) {
            state.toggleCellTypeToWall(p)
            currentGridState.value = state.drawCurrentGridState()
        }
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
    val isVisualizeEnabled = remember { mutableStateOf(true) }
    val onVisualized: () -> Unit = {
        scope.launch { state.animatedShortestPath() }
        isVisualizeEnabled.value = false
    }
    val onCleared: () -> Unit = {
        scope.launch { state.clear() }
        isVisualizeEnabled.value = true
    }
    val onRandomizeWalls: () -> Unit = {
        scope.launch { state.randomizeWalls() }
    }

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PathFindingGrid(cell.toLinearGrid(), onClick)

        Row(modifier = Modifier.padding(8.dp)) {
            Legend("Start", CELL_START)
            Legend("Finish", CELL_FINISH)
            VisualizeButton(
                modifier = Modifier.padding(start = 16.dp),
                onClick = onVisualized,
                enabled = isVisualizeEnabled.value
            )
            RandomWallsButton(
                modifier = Modifier.padding(start = 16.dp),
                onClick = onRandomizeWalls,
                enabled = isVisualizeEnabled.value
            )
            ClearButton(modifier = Modifier.padding(horizontal = 16.dp), onCleared)
            Legend("Visited", CELL_VISITED)
            Legend("Wall", CELL_WALL)
        }
    }
}