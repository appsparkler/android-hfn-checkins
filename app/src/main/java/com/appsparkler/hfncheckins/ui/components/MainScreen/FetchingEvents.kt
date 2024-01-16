package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appsparkler.hfncheckins.ui.components.common.VerticalSpacer12Dp

@Composable fun FetchingEvents() {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    CircularProgressIndicator()
    VerticalSpacer12Dp()
    Text(text = ("Fetching Events"))
  }
}

@Preview
@Composable fun FetchingEventsPreview() {
  FetchingEvents()
}