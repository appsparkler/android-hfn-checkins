package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsparkler.gsm.model.QRUser

@Composable
fun QRUserDetails(
  modifier: Modifier = Modifier,
  qrUser: QRUser = QRUser()
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
        value = qrUser.name
      )
      LabelledText(
        label = "Event Name:",
        value = qrUser.eventName
      )
      LabelledText(
        label = "Session Name:",
        value = qrUser.sessionName
      )
      LabelledText(
        label = "PNR:",
        value = qrUser.pnr
      )
      LabelledText(
        label = "Registration ID:",
        value = qrUser.registrationId
      )
    }
  }
}