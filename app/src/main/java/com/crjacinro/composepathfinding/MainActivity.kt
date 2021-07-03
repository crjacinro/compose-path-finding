package com.crjacinro.composepathfinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crjacinro.composepathfinding.ui.theme.ComposePathFindingTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePathFindingTheme {
                PathFindingApp()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PathFindingApp() {
    Surface(color = MaterialTheme.colors.background) {
        val bg = refreshedBackground().toLinearList()

        PathFindingGrid(bg)
    }
}


@ExperimentalFoundationApi
@Composable
fun PathFindingGrid(gridData: List<GridType>) {
    LazyVerticalGrid(cells = GridCells.Fixed(80)) {
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

@Suppress("FunctionName")
@Composable
fun Grid(gridType: GridType) {
    val boxModifier = Modifier
        .padding(0.dp)
        .border(BorderStroke(1.dp, Color.Red))
        .height(20.dp)
        .background(Color.Black)
        .fillMaxWidth()

    Box(modifier = boxModifier)
}

fun refreshedBackground(): MutableList<MutableList<GridType>> {
    val mutableGrid = MutableList(40) {
        MutableList(40) { GridType.BACKGROUND }
    }

    for (i in 0 until 40) {
        for (j in 0 until 40) {
            mutableGrid[i][j] = GridType.BACKGROUND
        }
    }

    return mutableGrid
}

fun List<List<GridType>>.toLinearList(): List<GridType> {
    val mutableList = mutableListOf<GridType>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            mutableList.add(this[i][j])
        }
    }
    return mutableList.toList()
}