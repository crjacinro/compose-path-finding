package com.crjacinro.composepathfinding.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@ExperimentalFoundationApi
@Composable
fun Legend(
    label: String,
    color: Color,
    hasBorder: Boolean = false
) {
    val boxModifier = Modifier
        .border(BorderStroke(if (hasBorder) 0.5.dp else 0.dp, Color.Gray))
        .padding(4.dp)
        .height(if (hasBorder) 10.dp else 16.dp)
        .background(color)
        .width(if (hasBorder) 10.dp else 16.dp)

    Box(modifier = boxModifier)
    Text(text = label, color = Color.Black)
}