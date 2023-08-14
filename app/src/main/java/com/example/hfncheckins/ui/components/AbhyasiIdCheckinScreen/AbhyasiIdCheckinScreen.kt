package com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.FieldData
import com.example.hfncheckins.ui.components.common.VerticalSpacer12Dp
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AbhyasiIdCheckinScreen(
  abhyasiIdCheckinViewModel: AbhyasiIdCheckinViewModel = viewModel(),
  modifier: Modifier = Modifier,
  onClickCheckin: (
    abhyasiIdCheckin: AbhyasiIdCheckin
  ) -> Unit,
  onClickCancel: () -> Unit,
) {
  val abhyasiIdCheckin by abhyasiIdCheckinViewModel.uiState.collectAsState()
  val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
  val handleCheckin = {
    localSoftwareKeyboardController?.hide()
    onClickCheckin(abhyasiIdCheckin)
  }
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(top = 100.dp),
    verticalArrangement = Arrangement.spacedBy(
      12.dp,
    ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    VerticalSpacer12Dp()
    Text(
      modifier = Modifier,
      text = "Checkin With \n Abhyasi ID",
      style = MaterialTheme.typography.headlineLarge,
      textAlign = TextAlign.Center
    )
    ElevatedCard(
      modifier = Modifier
        .fillMaxWidth(),
      colors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
      )
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
          4.dp,
        )
      ) {
        FieldData(fieldName = "Abhyasi Id: ", fieldValue = abhyasiIdCheckin.abhyasiId)
        FieldData(fieldName = "Batch: ", fieldValue = abhyasiIdCheckin.batch)
        OutlinedTextField(
          modifier = Modifier.fillMaxWidth(),
          value = abhyasiIdCheckin.dormAndBerthAllocation,
          keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
          ),
          onValueChange = {
            abhyasiIdCheckinViewModel.update(
              dormAndBerthAllocation = it
            )
          },
          label = {
            Text(text = "Dorm and Berth Allocation:")
          },
          keyboardActions = KeyboardActions(
            onDone = {
              handleCheckin()
            }
          )
        )
        CheckinAndCancelButtons(
          isCheckinValid = true,
          onClickCancel = onClickCancel,
          onClickCheckin = {
            handleCheckin()
          }
        )
      }
    }
  }
}

@Preview
@Composable
fun AbhyasiIdCheckinScreenPreview() {
  val abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
  abhyasiIdCheckinViewModel.update(
    abhyasiId = "INUEQS228",
    batch = "Batch 1"
  )
  HFNTheme() {
    Scaffold {
      AbhyasiIdCheckinScreen(
        modifier = Modifier.padding(it),
        onClickCheckin = {

        },
        onClickCancel = {},
        abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel
      )
    }
  }
}