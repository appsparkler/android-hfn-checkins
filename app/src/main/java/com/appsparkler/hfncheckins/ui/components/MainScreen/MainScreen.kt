package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  hfnEvent: HFNEvent,
  mainScreenViewModel: MainScreenViewModel = viewModel(),
  onStartCheckin: (String, InputValueType) -> Unit,
  onClickScan: (batch: String?) -> Unit
) {
  val mainScreenUiState by mainScreenViewModel.uiState.collectAsState()
  if (hfnEvent.defaultBatch != null && mainScreenUiState.batch == null) {
    mainScreenViewModel.update(batch = hfnEvent.defaultBatch)
  }
  Column(
    modifier = modifier
      .fillMaxHeight(),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Center
    ) {
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
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
//    uiMode = UI_MODE_NIGHT_YES
)
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
        hfnEvent = getSampleEvent(),
        onStartCheckin = { inputValue, type -> },
        onClickScan = {},
      )
    }
  }
}