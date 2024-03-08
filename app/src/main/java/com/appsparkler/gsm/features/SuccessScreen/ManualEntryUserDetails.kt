package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsparkler.gsm.features.TextTitleLarge

@Composable
fun ManualEntryUserDetails(
  modifier: Modifier = Modifier
) {
  ElevatedCard(
    modifier = modifier
      .padding(16.dp)
  ) {
    TextTitleLarge(
      modifier = Modifier
        .background(
          MaterialTheme.colorScheme.primaryContainer
        )
        .padding(8.dp),
      text = "Checkin Details",
      color = MaterialTheme.colorScheme.primary
    )
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      LabelledText(
        label = "Name:",
        value = "Janice Dsouza"
      )
      LabelledText(
        label = "Mobile No:",
        value = "1234567890"
      )
      LabelledText(
        label = "Email:",
        value = "janice@yahoo.com"
      )
      LabelledText(
        label = "Organization:",
        value = "Shakti Path"
      )
    }
  }
}