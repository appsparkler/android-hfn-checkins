package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.model.HFNEvent

@Composable
fun SelectEventCard(
  modifier: Modifier = Modifier,
  events: Array<HFNEvent>? = null,
  onEventSelected: (HFNEvent) -> Unit,
  selectedEvent: String?
) {
  if(events.isNullOrEmpty()) {
    return
  }
  Card(
    modifier = modifier.fillMaxWidth()
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