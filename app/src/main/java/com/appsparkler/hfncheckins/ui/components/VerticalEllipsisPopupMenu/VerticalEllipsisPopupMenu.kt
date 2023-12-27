package com.appsparkler.hfncheckins.ui.components.VerticalEllipsisPopupMenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VerticalEllipsisPopupMenu(
  onClick: () -> Unit = {}
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
            onClick()
          },
          text = { Text("Fetch Latest Config") },
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Refresh,
              contentDescription = "Fetch Latest Config",
            )
          }
        )
      }
    }
  }
}

@Preview
@Composable
fun VerticalEllipsisPopupMenuPreview() {
  VerticalEllipsisPopupMenu()
}
