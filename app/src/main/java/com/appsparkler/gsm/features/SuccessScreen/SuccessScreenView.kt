package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.gsm.features.GSMLogo
import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser
import com.appsparkler.hfncheckins.R
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun SuccessScreenView(
  modifier: Modifier = Modifier,
  qrUser: QRUser? = null,
  manualEntryUser: ManualEntryUser? = null,
  onClickReturnToMain: () -> Unit = {},
) {
  val colors = if (isSystemInDarkTheme()) {
    listOf(
      MaterialTheme.colorScheme.inversePrimary.toArgb(),
      MaterialTheme.colorScheme.inverseOnSurface.toArgb()
    )
  } else {
    listOf(
      MaterialTheme.colorScheme.primary.toArgb(),
      MaterialTheme.colorScheme.secondary.toArgb(),
      MaterialTheme.colorScheme.tertiary.toArgb(),
    )
  }
  val party = Party(
    speed = 0f,
    maxSpeed = 30f,
    damping = 0.9f,
    spread = 360,
    colors = colors,
    position = Position.Relative(0.5, 0.3),
    emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
  )
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.surface)
      .padding(16.dp)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    GSMLogo(modifier = Modifier)
    Icon(
      painter = painterResource(
        id = R.drawable.baseline_check_circle_24
      ),
      modifier = Modifier.size(150.dp),
      contentDescription = "Checkin success",
      tint = MaterialTheme.colorScheme.inversePrimary
    )
    if (qrUser != null) {
      QRUserDetails(
        qrUser = qrUser
      )
    }
    if (manualEntryUser != null) {
      ManualEntryUserDetails(
        user = manualEntryUser
      )
    }
    ScreenshotInstruction()
    Button(
      onClick = onClickReturnToMain
    ) {
      Text(
        text = "RETURN TO MAIN SCREEN",
      )
    }
  }
  KonfettiView(
    modifier = Modifier.fillMaxSize(),
    parties = listOf(party),
  )
}

@Preview
@Composable
fun SuccessScreenPreview_QRUser() {
  SuccessScreenView(
    qrUser = QRUser(
      name = "Janice Dsouza",
      eventName = "Event Name",
      sessionName = "Session Name",
      pnr = "EEE-22-2292",
      registrationId = "1211-1212-1213-1214",
    )
  )
}

@Preview
@Composable
fun SuccessScreenPreview_ManualEntryUser() {
  SuccessScreenView(
    manualEntryUser = ManualEntryUser(
      name = "Janice Dsouza",
      mobileNo = "1234567890",
      email = "abhishek@me.com",
      organization = "Shaktii Path"
    )
  )
}