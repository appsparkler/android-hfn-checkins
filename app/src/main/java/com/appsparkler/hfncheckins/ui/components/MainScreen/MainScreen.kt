package com.appsparkler.hfncheckins.ui.components.MainScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.data.sample.getSampleEvent
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.ui.components.common.VerticalSpacer12Dp
import com.appsparkler.hfncheckins.model.InputValueType
import com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu.SelectEventCard
import com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu.VerticalEllipsisMenuWithSelectDialog

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  hfnEvent: HFNEvent? = null,
  mainScreenViewModel: MainScreenViewModel = viewModel(),
  eventsViewModel: EventsViewModel = viewModel(),
  onStartCheckin: (String, InputValueType) -> Unit,
  onClickScan: (batch: String?) -> Unit
) {
  val context = LocalContext.current
  val eventsManager = EventsManager(context)
  val events = eventsManager.getEvents()
  Log.d("MainScreen", "MainScreen: $events")
  val eventsViewModelState by eventsViewModel.uiState.collectAsState()
  val mainScreenUiState by mainScreenViewModel.uiState.collectAsState()
  if (hfnEvent?.defaultBatch != null && mainScreenUiState.batch == null) {
    mainScreenViewModel.update(batch = hfnEvent.defaultBatch)
  }
  val handleSelectEvent = { it: HFNEvent ->
    eventsManager.setSelectedEvent(it)
    eventsViewModel.setSelectedEvent((it))
  }
  Column(
    modifier = modifier
      .fillMaxHeight()
      .fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    VerticalEllipsisMenuWithSelectDialog(
      events = events,
      selectedEvent = eventsViewModelState.selectedEvent?.id,
      onSelectEvent = {
        eventsManager.setSelectedEvent(it)
        eventsViewModel.setSelectedEvent((it))
      })
    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      verticalArrangement = Arrangement.Center
    ) {
      if(eventsViewModelState.ongoingEvents.isNullOrEmpty()) {
        FetchingEvents()
      } else {
        if (eventsViewModelState.selectedEvent != null) {
          SeekerInfoField(
            hfnEvent = hfnEvent,
            onStartCheckin = onStartCheckin,
            onChangeValue = {
              mainScreenViewModel.update(
                value = it
              )
            },
            seekerInfoUiState = mainScreenUiState,
            onChangeBatch = {
              mainScreenViewModel.update(batch = it)
            }
          )
        } else {
          SelectEventCard(
            modifier = Modifier
              .shadow(elevation = 16.dp, shape = MaterialTheme.shapes.large),
            events = events,
            onEventSelected = handleSelectEvent,
            selectedEvent = eventsViewModelState.selectedEvent?.id
          )
        }
      }
    }
    if (eventsViewModelState.selectedEvent != null) {
      Row(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
      ) {
        ScanButton(onClick = {
          onClickScan(mainScreenUiState.batch)
        })
      }
      VerticalSpacer12Dp()
    }
  }
}

@Preview
@Composable
fun MainScreenPreview() {
  HFNTheme() {
    val backgroundImage =
      painterResource(id = R.drawable.bg_light) // Replace with your image resource
    Scaffold {
      Image(
        modifier = Modifier
          .fillMaxSize(),
        painter = backgroundImage,
        contentDescription = "background image",
        contentScale = ContentScale.Crop
      )
      MainScreen(
        modifier = Modifier
          .padding(it)
          .padding(12.dp),
        hfnEvent = null,
        onStartCheckin = { inputValue, type -> },
        onClickScan = {},
      )
    }
  }
}