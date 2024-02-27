package com.appsparkler.hfncheckins.ui.features.CheckinStartScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.model.mock.eventsMockData

@Composable
fun CheckinStartScreen(
  modifier: Modifier = Modifier,
  event: HFNEvent
) {

}

@Preview
@Composable
fun CheckinStartScreenPreview() {
  Scaffold {
    CheckinStartScreen(
      modifier = Modifier.padding(it),
      event = eventsMockData.data[0]
    )
  }
}