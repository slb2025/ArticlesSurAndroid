package com.example.tpandroid.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getCustomGradientBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color(0xFF6A1B9A), Color(0xFFADADAD))
    )
}