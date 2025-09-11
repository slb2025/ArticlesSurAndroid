package com.example.tpandroid.theme

import android.R.attr.text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.LightGray,
        disabledContainerColor = Color.Gray,
        unfocusedLabelColor = Color.Black,
        focusedLabelColor = Color.White,
        focusedTextColor = Color.DarkGray,
        unfocusedTextColor = Color.DarkGray
    )
}