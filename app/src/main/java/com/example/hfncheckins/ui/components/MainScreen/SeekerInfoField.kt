package com.example.hfncheckins.ui.components.MainScreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.data.strings
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.model.HFNEvent
import com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.SelectField
import com.example.hfncheckins.utils.isEmailValid
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import com.example.hfncheckins.model.InputValueType
import com.example.hfncheckins.model.SeekerInfoFieldState

@Composable
fun SeekerInfoField(
  modifier: Modifier = Modifier,
  hfnEvent: HFNEvent,
  seekerInfoUiState: SeekerInfoFieldState,
  onStartCheckin: (String, InputValueType, batch: String?) -> Unit,
  onChangeValue: (String) -> Unit,
  onChangeBatch: (String) -> Unit
) {

  ElevatedCard(
    modifier = modifier,
    colors = CardDefaults.elevatedCardColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
  ) {
    Column(
      modifier = Modifier
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement
        .spacedBy(12.dp)
    ) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = hfnEvent.title,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
      )
      Column {
        val imeAction = if (isValidInput(seekerInfoUiState.value)) {
          ImeAction.Go
        } else {
          ImeAction.None
        }
        if (hfnEvent.batches != null) {
          SelectField(
            label = "Batch",
            options = hfnEvent.batches,
            onChange = {
              onChangeBatch(it)
            },
            value = seekerInfoUiState.batch.toString()
          )
        }
        OutlinedTextField(
          modifier = Modifier
            .testTag(strings.tag_seeker_input),
          value = seekerInfoUiState.value,
          onValueChange = onChangeValue,
          label = {
            Text(text = strings.pleaseEnterInfo)
          },
          keyboardActions = KeyboardActions(
            onGo = {
              onStartCheckin(
                seekerInfoUiState.value,
                seekerInfoUiState.type!!,
                seekerInfoUiState.batch
              )
            }
          ),
          keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
          )
        )
        Text(
          text = strings.inputInstructions,
          style = MaterialTheme.typography.labelSmall
        )
      }

      Button(
        onClick = {
          onStartCheckin(seekerInfoUiState.value, seekerInfoUiState.type!!, seekerInfoUiState.batch)
        },
        enabled = seekerInfoUiState.isValid,
      ) {
        Text(text = strings.startCheckin)
      }
    }
  }
}

private fun isValidInput(inputValue: String) = isValidPhoneNumber(inputValue) ||
        isEmailValid(inputValue) ||
        isValidAbhyasiId(inputValue)

@Preview(
  uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun SeekerInfoFieldPreview() {
  HFNTheme() {
    Scaffold {
      SeekerInfoField(
        modifier = Modifier
          .padding(it)
          .padding(12.dp),
        hfnEvent = getSampleEvent(),
        seekerInfoUiState = SeekerInfoFieldState(
          batch = null,
          isValid = false,
          type = null,
          value = "",
        ),
        onStartCheckin = { inputValue, type, batch ->

        },
        onChangeValue = {

        },
        onChangeBatch = {

        }
      )
    }
  }
}
