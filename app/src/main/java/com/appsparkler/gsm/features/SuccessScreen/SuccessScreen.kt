package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.R
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun SuccessScreen(
  modifier: Modifier = Modifier,
  onClickReturnToMain: () -> Unit = {}
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
      .background(MaterialTheme.colorScheme.surface),
    horizontalAlignment = Alignment.CenterHorizontally
//    verticalArrangement = Arrangement.spacedBy(
//      12.dp,
//      Alignment.CenterVertically
//    )
  ) {
    Icon(
      painter = painterResource(
        id = R.drawable.baseline_check_circle_24
      ),
      modifier = Modifier.size(150.dp),
      contentDescription = "Checkin success",
      tint = MaterialTheme.colorScheme.inversePrimary
    )
    Button(onClick = onClickReturnToMain) {
      Text(
        text = "RETURN TO MAIN SCREEN",
      )
    }
    UserDetails()
  }
  KonfettiView(
    modifier = Modifier.fillMaxSize(),
    parties = listOf(party),
  )
}

@Composable
fun UserDetails() {
  Column {
    LabelledText(
      label = "Name:",
      value = "Janice Dsouza"
    )
    LabelledText(
      label = "Mobile No:",
      value = "1234567890"
    )
  }
}

@Composable
fun LabelledText(
  modifier: Modifier = Modifier,
  label: String, value: String
) {
  if (value.isNotEmpty()) {
    Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = label,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = value
      )
    }
  }
}

@Preview
@Composable
fun SuccessScreenPreview() {
  SuccessScreen()
}