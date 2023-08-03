package com.example.hfncheckins.ui.components.EmailWithMobileOrEmailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun EmailWithMobileOrEmailScreen(
    modifier: Modifier = Modifier,
    startWithMobile: Boolean
) {
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
                var ageMenuExpanded by remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        2.dp
                    )
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Full Name")
                        }
                    )
                    AgeAndGenderRow(
                        age = "", onChangeAge = {},
                        gender = "", onChangeGender = {}
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "City")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "State")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Country")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        enabled = !startWithMobile,
                        label = {
                            Text(text = "Mobile")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        enabled = startWithMobile,
                        label = {
                            Text(text = "Email")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
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
                onClickCancel = { /*TODO*/ },
                onClickCheckin = {}
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
                    .padding(16.dp)
            )
        }
    }
}