package com.appsparkler.hfncheckins.ui.features.CheckinStartScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Heading(
  title: String
) {
  Text(
    modifier = Modifier.fillMaxWidth(),
    text = title,
    style = MaterialTheme.typography.displaySmall,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    color = MaterialTheme.colorScheme.primary
  )
}