package com.appsparkler.hfncheckins.ui.components.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Heading(
    heading: String
) {
    Text(
        text = heading,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge
    )
}