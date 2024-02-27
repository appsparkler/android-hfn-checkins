package com.appsparkler.hfncheckins.ui.features.EventSelectorScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.model.mock.eventsMockData

@Composable
fun EventSelectorCard(
  modifier: Modifier = Modifier,
  events: Array<HFNEvent>? = null,
  onEventSelected: (HFNEvent) -> Unit = {},
  selectedEvent: String? = null
) {
  if (events.isNullOrEmpty()) {
    Text(text = "No events to select from...")
  } else {
    ElevatedCard(
      modifier = modifier.fillMaxWidth()
    ) {
      Text(
        modifier = Modifier
          .background(
            MaterialTheme.colorScheme.primaryContainer
          )
          .padding(16.dp)
          .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        text = "Select Event",
        style = MaterialTheme.typography.titleMedium
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
              Text(
                text = it.id,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
              )
            }
          )
        }
      }
    }
  }
}

@Composable
@Preview
fun EventSelectorCardPreview_has_events() {
  EventSelectorCard(
    events = eventsMockData.data
  )
}

@Composable
@Preview
fun EventSelectorCardPreview_no_events() {
  EventSelectorCard(
    events = emptyArray()
  )
}