package com.appsparkler.gsm.features

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextTitleLarge(
  modifier: Modifier = Modifier,
  text: String,
  color: Color = MaterialTheme.colorScheme.onSurface
) {
  Text(
    modifier = modifier
      .fillMaxWidth(),
    text = text,
    style = MaterialTheme.typography.titleLarge,
    textAlign = TextAlign.Center,
    color = color
  )
}