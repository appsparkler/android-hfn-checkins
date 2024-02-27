package com.appsparkler.hfncheckins.ui.features.EventSelectorScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.model.mock.eventsMockData
import kotlinx.coroutines.delay


@Composable
fun EventSelectorScreen(
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
    if (isFetching) FetchingProgressUI()
    else EventSelectorCard(
      events = events,
      onEventSelected = onEventSelected,
      selectedEvent = selectedEvent
    )
  }
}

@Preview
@Composable
fun EventSelectorScrenPreview() {
  var isFetching by remember {
    mutableStateOf(true)
  }
  var events by remember {
    mutableStateOf<Array<HFNEvent>?>(null)
  }
  LaunchedEffect(key1 = Unit) {
    delay(2000)
    isFetching = false
    events = eventsMockData.data
  }

  Scaffold {
    EventSelectorScreen(
      modifier = Modifier
        .padding(it)
        .padding(16.dp),
      isFetching = isFetching,
      events = events,
    )
  }
}