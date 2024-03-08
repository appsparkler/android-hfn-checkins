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
import com.appsparkler.gsm.model.ManualEntryUser

@Composable
fun ManualEntryUserDetails(
  modifier: Modifier = Modifier,
  user: ManualEntryUser
) {
  ElevatedCard(
    modifier = modifier
      .padding(16.dp)
  ) {
    CheckinDetailsTitle()
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      LabelledText(
        label = "Name:",
        value = user.name
      )
      LabelledText(
        label = "Mobile No:",
        value = user.mobileNo
      )
      LabelledText(
        label = "Email:",
        value = user.email
      )
      LabelledText(
        label = "Organization:",
        value = user.organization
      )
    }
  }
}