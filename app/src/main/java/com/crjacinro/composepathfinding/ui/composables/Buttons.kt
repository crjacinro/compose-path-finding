package com.crjacinro.composepathfinding.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@ExperimentalFoundationApi
@Composable
fun VisualizeButton(modifier: Modifier = Modifier, onClick: () -> (Unit), enabled: Boolean = true) {
    ButtonWithText(
        modifier,
        onClick = onClick,
        label = "Visualize",
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
    )
}

@ExperimentalFoundationApi
@Composable
fun ClearButton(modifier: Modifier = Modifier, onClick: () -> (Unit)) {
    ButtonWithText(
        modifier,
        onClick = onClick,
        label = "Clear",
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    )
}

@ExperimentalFoundationApi
@Composable
private fun ButtonWithText(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    onClick: () -> (Unit),
    colors: ButtonColors
) {
    Button(onClick = onClick, modifier = modifier, colors = colors, enabled = enabled) {
        Text(text = label, color = Color.White)
    }
}
