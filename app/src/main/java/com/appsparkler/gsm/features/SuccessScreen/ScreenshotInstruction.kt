package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenshotInstruction(
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .width(200.dp),
    color = MaterialTheme.colorScheme.errorContainer,
    tonalElevation = 8.dp
  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .background(MaterialTheme.colorScheme.errorContainer),
      color = MaterialTheme.colorScheme.error,
      text = "Please take a screenshot of this screen and show it at the desk along with a valid ID to collect your wrist band."
    )
  }
}