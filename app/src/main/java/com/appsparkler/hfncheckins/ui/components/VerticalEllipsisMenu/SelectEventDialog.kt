package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.appsparkler.hfncheckins.model.HFNEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectEventDialog(
  events: Array<HFNEvent>,
  selectedEvent: String? = null,
  onEventSelected: (HFNEvent) -> Unit,
  onDismissRequest: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismissRequest
  ) {
    Column {
      SelectEventCard(
        events = events,
        onEventSelected = onEventSelected,
        selectedEvent = selectedEvent
      )
    }
  }
}

@Preview(
  device = "spec:width=1080dp,height=1920dp,dpi=480",

  )
@Composable
fun SelectEventDialogPreview() {
  Scaffold {
    it.toString()
    val events = arrayOf(
      HFNEvent(id = "evt_1", title = "Event 1"),
      HFNEvent(id = "evt_2", title = "Event 2"),
    )
    SelectEventDialog(
      events,
      selectedEvent = events[1].id,
      onEventSelected = {},
      onDismissRequest = {}
    )
  }
}
