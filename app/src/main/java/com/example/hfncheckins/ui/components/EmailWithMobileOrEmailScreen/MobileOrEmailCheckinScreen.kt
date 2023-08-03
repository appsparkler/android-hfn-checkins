package com.example.hfncheckins.ui.components.EmailWithMobileOrEmailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.CustomLazyColumn
import com.example.hfncheckins.ui.components.common.Heading
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

data class EmailOrMobileCheckin(
    val email: String,
    val mobile: String,
    val fullName: String,
    val ageGroup: String,
    val gender: String,
    val city: String,
    val state: String,
    val country: String,
    val dormOrBerthAllocation: String,
    val timestamp: Long
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailWithMobileOrEmailScreen(
    modifier: Modifier = Modifier,
    startWithMobile: Boolean,
    emailOrMobileCheckin: EmailOrMobileCheckin,
    onClickCheckin: () -> Unit,
    onClickCancel: () -> Unit
) {
    val nextImeAction = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
    )
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
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        2.dp
                    )
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = emailOrMobileCheckin.fullName,
                        onValueChange = {},
                        label = {
                            Text(text = "Full Name")
                        },
                        keyboardOptions = nextImeAction,
                    )
                    AgeAndGenderRow(
                        age = emailOrMobileCheckin.ageGroup, onChangeAge = {},
                        gender = emailOrMobileCheckin.gender, onChangeGender = {}
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.city,
                        onValueChange = {},
                        keyboardOptions = nextImeAction,
                        label = {
                            Text(text = "City")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.state,
                        onValueChange = {},
                        label = {
                            Text(text = "State")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.country,
                        onValueChange = {},
                        label = {
                            Text(text = "Country")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.mobile,
                        onValueChange = {},
                        enabled = !startWithMobile,
                        label = {
                            Text(text = "Mobile")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.email,
                        onValueChange = {},
                        enabled = startWithMobile,
                        label = {
                            Text(text = "Email")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailOrMobileCheckin.dormOrBerthAllocation,
                        onValueChange = {},
                        label = {
                            Text(text = "Dorm and Berth Allocation")
                        }
                    )
                }
            }
        }
        item {
            CheckinAndCancelButtons(
                onClickCancel = onClickCancel,
                onClickCheckin = onClickCheckin
            )
        }
    }
}

@Preview
@Composable
fun EmailWithMobileOrEmailScreenPreview() {
    HFNCheckinsTheme() {
        Scaffold() {
            EmailWithMobileOrEmailScreen(
                startWithMobile = true,
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp),
                emailOrMobileCheckin = EmailOrMobileCheckin(
                    gender = "",
                    timestamp = 0,
                    fullName = "John Doe",
                    ageGroup = "",
                    mobile = "+911234567890",
                    email = "johndoe@hfn",
                    dormOrBerthAllocation = "Dorm",
                    city = "New York",
                    state = "NY",
                    country = "USA"
                ),
                onClickCheckin = {},
                onClickCancel = {}
            )
        }
    }
}