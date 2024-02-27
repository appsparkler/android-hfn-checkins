package com.appsparkler.hfncheckins.ui.features.EventSelectorScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable fun EventSelectorScreen(
  modifier: Modifier = Modifier,
  isFetching: Boolean = false
) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if(isFetching) FetchingProgressUI()
    else EventSelectorCard()
  }
}

@Preview @Composable fun EventSelectorScrenPreview() {
  Scaffold {
    EventSelectorScreen(
      modifier = Modifier.padding(it)
    )
  }
}