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
import com.appsparkler.hfncheckins.model.HFNEvent


@Composable fun EventSelectorScreen(
  modifier: Modifier = Modifier,

  isFetching: Boolean = false,
  // EventSelectorCard
  events: Array<HFNEvent>? = null,
  onEventSelected: (HFNEvent) -> Unit = {},
  selectedEvent: String? = null
) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if(isFetching) FetchingProgressUI()
    else EventSelectorCard(
      events = events,
      onEventSelected = onEventSelected,
      selectedEvent = selectedEvent
    )
  }
}

@Preview @Composable fun EventSelectorScrenPreview() {
  Scaffold {
    EventSelectorScreen(
      modifier = Modifier.padding(it),
      isFetching = false,
      
    )
  }
}