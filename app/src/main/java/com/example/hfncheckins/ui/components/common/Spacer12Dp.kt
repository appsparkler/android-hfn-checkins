package com.example.hfncheckins.ui.components.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable fun VerticalSpacer12Dp() {
  Spacer(modifier = Modifier
    .fillMaxWidth()
    .height(12.dp)
  )
}