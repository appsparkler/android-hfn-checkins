package com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.model.MobileOrEmailCheckinDBModel
import com.appsparkler.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.appsparkler.hfncheckins.ui.components.common.CustomLazyColumn
import com.appsparkler.hfncheckins.ui.components.common.FieldData
import com.appsparkler.hfncheckins.ui.components.common.Heading
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

fun extractAge(input: String?): String? {
  if (!input.isNullOrBlank()) {
    val pattern = Regex("\\d+-(\\d+)")
    val matchResult = pattern.find(input)

    return matchResult?.groupValues?.get(1)
  } else {
    return null
  }
}

fun extractGender(input: String?): String? {
  if (!input.isNullOrBlank()) {
    when (input) {
      "Male" -> return "M"
      "Female" -> return "F"
      "Unspecified" -> return "U"
      else -> return null
    }
  }
  return null
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailWithMobileOrEmailScreen(
  modifier: Modifier = Modifier,
  checkinWithMobileOrEmailViewModel: CheckinWithMobileOrEmailViewModel = viewModel(),
  onClickCheckin: (MobileOrEmailCheckinDBModel) -> Unit,
  onClickCancel: () -> Unit
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  val emailOrMobileCheckin by checkinWithMobileOrEmailViewModel.uiState.collectAsState()
  val imeDoneAction = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Done
  )
  val imeNoneAction = KeyboardOptions.Default.copy(
    imeAction = ImeAction.None
  )
  val imeNextAction = KeyboardOptions.Default.copy(
    imeAction = ImeAction.Next
  )
  val optionalText = " (optional)"
  val emailLabel = "Email" + if (emailOrMobileCheckin.startWithMobile) optionalText else ""
  val mobileLabel = "Mobile" + if (!emailOrMobileCheckin.startWithMobile) optionalText else ""
  val handleClickCheckin = {
    keyboardController?.hide()
    onClickCheckin(
      MobileOrEmailCheckinDBModel(
        timestamp = System.currentTimeMillis(),
        type = emailOrMobileCheckin.type,
        batch = emailOrMobileCheckin.batch ?: "",
        email = emailOrMobileCheckin.email.uppercase(),
        fullName = emailOrMobileCheckin.fullName.uppercase(),
        ageGroup = extractAge(emailOrMobileCheckin.ageGroup) ?: "",
        mobile = emailOrMobileCheckin.mobile,
        dormOrBerthAllocation = emailOrMobileCheckin.dormOrBerthAllocation,
        gender = extractGender(emailOrMobileCheckin.gender) ?: "",
        city = emailOrMobileCheckin.city.uppercase(),
        state = emailOrMobileCheckin.state.uppercase(),
        country = emailOrMobileCheckin.country.uppercase(),
        event = emailOrMobileCheckin.event
      )
    )
  }
  CustomLazyColumn(
    modifier = modifier
  ) {
    item {
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(12.dp)
      )
      Heading(
        heading = "Checkin with \n Email or Mobile"
      )
    }
    item {
      ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
          containerColor = if (emailOrMobileCheckin.isValid) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer
        )
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(
            2.dp
          )
        ) {
          FieldData(fieldName = "Batch: ", fieldValue = emailOrMobileCheckin.batch)
          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth(),
            value = emailOrMobileCheckin.fullName,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(fullName = it)
            },
            label = {
              Text(text = "Full Name")
            },
            keyboardOptions = imeDoneAction
          )
          AgeAndGenderRow(
            age = emailOrMobileCheckin.ageGroup, onChangeAge = {
              checkinWithMobileOrEmailViewModel.update(ageGroup = it)
            },
            gender = emailOrMobileCheckin.gender, onChangeGender = {
              checkinWithMobileOrEmailViewModel.update(gender = it)
            }
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.city,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(city = it)
            },
            label = {
              Text(text = "City")
            },
            keyboardOptions = imeNextAction
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.state,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(state = it)
            },
            label = {
              Text(text = "State")
            },
            keyboardOptions = imeNextAction
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.country,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(country = it)
            },
            label = {
              Text(text = "Country")
            },
            keyboardOptions = imeNextAction
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.mobile,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(mobile = it)
            },
            enabled = !emailOrMobileCheckin.startWithMobile,
            keyboardOptions = imeNextAction,
            label = {
              Text(text = mobileLabel)
            }
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.email,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(email = it)
            },
            enabled = emailOrMobileCheckin.startWithMobile,
            label = {
              Text(text = emailLabel)
            },
            keyboardOptions = imeNextAction
          )
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrMobileCheckin.dormOrBerthAllocation,
            onValueChange = {
              checkinWithMobileOrEmailViewModel.update(dormOrBerthAllocation = it)
            },
            label = {
              Text(text = "Dorm and Berth Allocation")
            },
            keyboardOptions = if (emailOrMobileCheckin.isValid) imeDoneAction else imeNoneAction,
            keyboardActions = KeyboardActions(
              onDone = {
                handleClickCheckin()
              }
            )
          )
        }
      }
    }
    item {
      CheckinAndCancelButtons(
        isCheckinValid = emailOrMobileCheckin.isValid,
        onClickCancel = onClickCancel,
        onClickCheckin = {
          handleClickCheckin()
        }
      )
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(12.dp)
      )
    }
  }
}

@Preview
@Composable
fun EmailWithMobileOrEmailScreenPreview() {
  val checkinWithMobileOrEmailViewModel = CheckinWithMobileOrEmailViewModel()
  checkinWithMobileOrEmailViewModel.update(
    email = "abc@def.com",
    startWithMobile = false,
  )
  HFNTheme() {
    Scaffold() {
      EmailWithMobileOrEmailScreen(
        modifier = Modifier
          .padding(it)
          .padding(8.dp),
        checkinWithMobileOrEmailViewModel = checkinWithMobileOrEmailViewModel,
        onClickCheckin = {},
        onClickCancel = {},
      )
    }
  }
}
