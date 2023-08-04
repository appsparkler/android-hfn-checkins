package com.example.hfncheckins.ui.components.EmailWithMobileOrEmailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.CustomLazyColumn
import com.example.hfncheckins.ui.components.common.Heading
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.example.hfncheckins.utils.isEmailValid
import com.example.hfncheckins.utils.isValidPhoneNumber

data class EmailOrMobileCheckin(
    val startWithMobile: Boolean,
    val email: String,
    val mobile: String,
    val fullName: String,
    val ageGroup: String,
    val gender: String,
    val city: String,
    val state: String,
    val country: String,
    val dormOrBerthAllocation: String,
    val timestamp: Long,
    val isValid: Boolean
)

@Composable
fun EmailWithMobileOrEmailScreen(
    modifier: Modifier = Modifier,
    emailOrMobileCheckin: EmailOrMobileCheckin,
    onClickCheckin: () -> Unit,
    onClickCancel: () -> Unit,
    onChange: (EmailOrMobileCheckin) -> Unit
) {
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
    CustomLazyColumn(
        modifier = modifier
    ) {
        item {
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
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = emailOrMobileCheckin.fullName,
                        onValueChange = {
                            onChange(
                                emailOrMobileCheckin.copy(
                                    fullName = it
                                )
                            )
                        },
                        label = {
                            Text(text = "Full Name")
                        },
                        keyboardOptions = imeDoneAction
                    )
                    AgeAndGenderRow(
                        age = emailOrMobileCheckin.ageGroup, onChangeAge = {
                            onChange(
                                emailOrMobileCheckin.copy(
                                    ageGroup = it
                                )
                            )
                        },
                        gender = emailOrMobileCheckin.gender, onChangeGender = {
                            onChange(
                                emailOrMobileCheckin.copy(
                                    gender = it
                                )
                            )
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.city,
                        onValueChange = {
                            onChange(
                                emailOrMobileCheckin.copy(
                                    city = it
                                )
                            )
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
                            onChange(
                                emailOrMobileCheckin.copy(
                                    state = it
                                )
                            )
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
                            onChange(
                                emailOrMobileCheckin.copy(
                                    country = it
                                )
                            )
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
                            onChange(
                                emailOrMobileCheckin.copy(
                                    mobile = it
                                )
                            )
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
                            onChange(
                                emailOrMobileCheckin.copy(
                                    email = it
                                )
                            )
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
                            onChange(
                                emailOrMobileCheckin.copy(
                                    dormOrBerthAllocation = it
                                )
                            )
                        },
                        label = {
                            Text(text = "Dorm and Berth Allocation")
                        },
                        keyboardOptions = if (emailOrMobileCheckin.isValid) imeDoneAction else imeNoneAction
                    )
                }
            }
        }
        item {
            CheckinAndCancelButtons(
                isCheckinValid = emailOrMobileCheckin.isValid,
                onClickCancel = onClickCancel,
                onClickCheckin = onClickCheckin
            )
        }
    }
}

@Preview
@Composable
fun EmailWithMobileOrEmailScreenPreview() {
    var emailOrCheckin by remember {
        mutableStateOf(
            EmailOrMobileCheckin(
                gender = "",
                timestamp = 0,
                fullName = "",
                ageGroup = "",
                mobile = "",
                email = "abc@def.com",
                dormOrBerthAllocation = "",
                city = "",
                state = "",
                country = "",
                isValid = false,
                startWithMobile = false
            )
        )
    }
    HFNCheckinsTheme() {
        Scaffold() {
            EmailWithMobileOrEmailScreen(
                modifier = Modifier
                    .padding(it)
                    .padding(8.dp),
                emailOrMobileCheckin = emailOrCheckin,
                onClickCheckin = {},
                onClickCancel = {},
                onChange = {
                    emailOrCheckin = validateAndUpdate(it)
                }
            )
        }
    }
}

fun validateAndUpdate(it: EmailOrMobileCheckin): EmailOrMobileCheckin {
    var isValid = false;
    val isFullNameValid = it.fullName.isNotBlank()
    val isAgeValid = it.ageGroup.isNotBlank()
    val isGenderValid = it.ageGroup.isNotBlank()
    val isCityValid = it.city.isNotBlank()
    val isStateValid = it.state.isNotBlank()
    val isCountryValid = it.country.isNotBlank()
    val isMobileValid = it.mobile.isEmpty() || isValidPhoneNumber(it.mobile)
    val isEmailValid = it.email.isEmpty() || isEmailValid(it.email)
    if (isFullNameValid && isAgeValid && isGenderValid && isCityValid && isStateValid && isCountryValid && isMobileValid && isEmailValid) {
        isValid = true;
    }
    return it.copy(
        fullName = it.fullName,
        ageGroup = it.ageGroup,
        gender = it.gender,
        city = it.city,
        state = it.state,
        country = it.country,
        mobile = it.mobile,
        email = it.email,
        dormOrBerthAllocation = it.dormOrBerthAllocation,
        timestamp = System.currentTimeMillis(),
        isValid = isValid
    )
}
