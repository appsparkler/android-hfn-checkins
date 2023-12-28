package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
      Card(
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          modifier = Modifier.padding(16.dp),
          text = "Select Event", style = MaterialTheme.typography.titleMedium
        )
        LazyColumn {
          items(events) {
            ListItem(
              modifier = Modifier.clickable() {
                onEventSelected(it)
              },
              headlineContent = {
                Text(text = "${it.title} ${if (it.id == selectedEvent) "✅" else ""}")
              },
              supportingContent = {
                Text(text = it.id)
              }
            )
          }
        }
      }
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
