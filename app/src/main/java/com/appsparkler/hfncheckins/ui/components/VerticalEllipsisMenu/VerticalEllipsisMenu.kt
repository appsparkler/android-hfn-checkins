package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisMenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appsparkler.hfncheckins.model.HFNEvent

@Composable fun VerticalEllipsisMenuWithSelectDialog(
  events: Array<HFNEvent>,
  selectedEvent: String,
  onSelectEvent: (HFNEvent) -> Unit
) {
  val showDialog = remember { mutableStateOf(false) }

  VerticalEllipsisPopupMenu(
    onClickSelectEvent = {
      showDialog.value = true
    },
  )

  if (showDialog.value) {
    SelectEventDialog(
      onDismissRequest = { showDialog.value = false },
      events = events,
      selectedEvent = selectedEvent,
      onEventSelected = {
        showDialog.value = false
        onSelectEvent(it)
      }
    )
  }
}

@Preview(
  device = "spec:width=411dp,height=891dp,dpi=480"
)
@Composable
fun VerticalEllipsisMenuWithSelectDialogPreview() {
  VerticalEllipsisMenuWithSelectDialog(
    events = arrayOf(
      HFNEvent(
        id = "event_1",
        title = "Event 1",
      ),
      HFNEvent(
        id = "event_2",
        title = "Event 2",
      )
    ), selectedEvent = "",
    onSelectEvent = {

    }
  )
}

@Composable
fun VerticalEllipsisPopupMenu(
  onClickSelectEvent: () -> Unit = {}
) {
  var showMenu by remember { mutableStateOf(false) }

  IconButton(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentWidth(Alignment.End),
    enabled = true,
    onClick = { showMenu = true }
  ) {
    Box {
      Icon(
        imageVector = Icons.Default.MoreVert,
        contentDescription = "More options"
      )

      DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
      ) {
        DropdownMenuItem(
          onClick = {
            showMenu = false
            onClickSelectEvent()
          },
          text = { Text("Select Event") },
        )
      }
    }
  }
}

@Preview(
 device = "spec:width=411dp,height=891dp,dpi=480"
)
@Composable
fun VerticalEllipsisPopupMenuPreview() {
  VerticalEllipsisPopupMenu()
}
