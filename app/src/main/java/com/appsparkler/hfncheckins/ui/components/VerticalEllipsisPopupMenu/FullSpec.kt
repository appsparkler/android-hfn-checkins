package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisPopupMenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun OverlayButton() {
  var isOverlayVisible by remember { mutableStateOf(false) }

  VerticalEllipsisPopupMenu(
    onClick = { isOverlayVisible = true }
  )

  AnimatedVisibility(
    visible = isOverlayVisible,
    enter = fadeIn(animationSpec = tween(durationMillis = 500)),
    exit = fadeOut(animationSpec = tween(durationMillis = 500))
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clickable { /* Consume clicks to prevent interaction with elements below */ }
        .alpha(0.4f) // Adjust transparency as needed
    ) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = MaterialTheme.colorScheme.primary
      )
    }
  }

  LaunchedEffect(isOverlayVisible) {
    if (isOverlayVisible) {
      delay(3000) // Delay for 3 seconds
      isOverlayVisible = false // Hide the overlay
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun OverlayButtonPreview() {
  OverlayButton()
}