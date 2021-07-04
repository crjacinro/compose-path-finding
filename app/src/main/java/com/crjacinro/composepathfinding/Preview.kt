package com.crjacinro.composepathfinding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crjacinro.composepathfinding.ui.theme.ComposePathFindingTheme

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePathFindingTheme {
        PathFindingApp()
    }
}