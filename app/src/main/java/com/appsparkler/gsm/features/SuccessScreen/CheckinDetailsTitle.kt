package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsparkler.gsm.features.TextTitleLarge

@Composable
fun CheckinDetailsTitle() {
  TextTitleLarge(
    modifier = Modifier
      .background(
        MaterialTheme.colorScheme.primaryContainer
      )
      .padding(8.dp),
    text = "Checkin Details",
    color = MaterialTheme.colorScheme.primary
  )
}