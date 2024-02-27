package com.appsparkler.hfncheckins.ui.features.CheckinStartScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.data.strings
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.model.mock.eventsMockData
import com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.SelectField


@Composable
fun SeekerInfoField(
  modifier: Modifier = Modifier,
  hfnEvent: HFNEvent = eventsMockData.data[0],
  selectedBatch: String? = null,
  inputValue: String = "",
  isFormValid: Boolean = false,
  onClickStart: () -> Unit = {},
  onChangeValue: (String) -> Unit = {},
  onChangeBatch: (String) -> Unit = {}
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
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

      val imeAction = if (
        isFormValid
      ) {
        ImeAction.Go
      } else {
        ImeAction.None
      }
      Heading(title = hfnEvent.title)
      if (hfnEvent.batches != null) {
        SelectField(
          label = "Batch",
          options = hfnEvent.batches,
          onChange = {
            onChangeBatch(it)
          },
          value = selectedBatch ?: "Select Batch"
        )
      }
      OutlinedTextField(
        modifier = Modifier
          .testTag(strings.tag_seeker_input),
        value = inputValue,
        onValueChange = onChangeValue,
        label = {
          Text(text = strings.pleaseEnterInfo)
        },
        keyboardActions = KeyboardActions(
          onGo = {
            onClickStart()
          }
        ),
        keyboardOptions = KeyboardOptions(
          imeAction = imeAction,
        ),
        supportingText = {
          Text(
            text = strings.inputInstructions,
            color = MaterialTheme.colorScheme.secondary,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.labelSmall
          )
        }
      )
      Button(
        onClick = onClickStart,
        enabled = isFormValid,
      ) {
        Text(text = strings.startCheckin)
      }
    }
  }
}

@Preview
@Composable
fun SeekerInfoFieldPreview_with_batch() {
  val context = LocalContext.current
  fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }
  SeekerInfoField(
    hfnEvent = eventsMockData.data[0],
    isFormValid = false,
    onChangeBatch = {
      showToast(context, it)
    },
    onChangeValue = {
      showToast(context, it)
    }
  )
}


@Preview
@Composable
fun SeekerInfoFieldPreview_withoutBatch() {
  SeekerInfoField(
    hfnEvent = eventsMockData.data[1],
    isFormValid = false,
  )
}

@Preview
@Composable
fun SeekerInfoFieldPreview_valid_input() {
  SeekerInfoField(
    hfnEvent = eventsMockData.data[1],
    isFormValid = true,
    inputValue = "+913333838388",
  )
}