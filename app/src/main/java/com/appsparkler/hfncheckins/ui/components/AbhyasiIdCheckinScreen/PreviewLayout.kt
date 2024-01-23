package com.appsparkler.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun PreviewLayout(
  content: @Composable ColumnScope.() -> Unit
) {
  var darkTheme by remember {
    mutableStateOf(false)
  }
  HFNTheme(
    darkTheme
  ) {
    Scaffold {
      Column() {
      }
      Column(
        modifier = Modifier
          .padding(it)
          .padding(12.dp),
      ) {
        Switch(checked = darkTheme, onCheckedChange = { darkTheme = it })
        content()
      }
    }
  }
}

@Composable
@Preview
fun PreviewLayoutPreview() {
  PreviewLayout {
    Text(
      text = "Hello World",
      style = MaterialTheme.typography.headlineMedium
    )
  }
}