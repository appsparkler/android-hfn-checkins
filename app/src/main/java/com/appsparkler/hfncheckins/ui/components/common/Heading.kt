package com.appsparkler.hfncheckins.ui.components.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Heading(
    heading: String,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.headlineLarge
) {
    Text(
        text = heading,
        textAlign = TextAlign.Center,
        color = color,
        style = style
    )
}