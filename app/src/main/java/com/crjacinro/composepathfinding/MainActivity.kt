package com.crjacinro.composepathfinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crjacinro.composepathfinding.ui.theme.ComposePathFindingTheme

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
}

@ExperimentalFoundationApi
@Composable
fun PathFindingApp() {
    val bg = getGridWithClearBackground().toLinearGrid()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PathFindingGrid(bg)
        Button(onClick = { }) {
            Text("Visualize")
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PathFindingGrid(gridData: List<GridType>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(NUMBER_OF_COLUMNS),
        modifier = Modifier
            .padding(4.dp)
            .border(BorderStroke(6.dp, Color.Black))
    ) {
        items(gridData) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Grid(gridType = GridType.BACKGROUND)
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePathFindingTheme {
        PathFindingApp()
    }
}