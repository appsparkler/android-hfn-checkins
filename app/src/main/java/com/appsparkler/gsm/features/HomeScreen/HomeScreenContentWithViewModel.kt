package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreenContentWithViewModel(
  modifier: Modifier = Modifier,
  vm: HomeScreenViewModel = viewModel(),
  onCheckin: () -> Unit = {}
) {
  val state by vm.state.collectAsState()

  HomeScreenContent(
    modifier = modifier,
    name = state.name,
    onChangeName = vm::updateName,
    mobileNo = state.mobileNo,
    onChangeMobileNo = vm::updateMobileNo,
    email = state.email,
    onChangeEmail = vm::updateEmail,
    organization = state.organization,
    onChangeOrganization = vm::updateOrganization,
    checkinButtonEnabled = state.checkinButtonEnabled,
    onCheckin = onCheckin
  )
}

@Preview
@Composable
fun HomeScreenWithViewModelPreview() {
  HomeScreenContentWithViewModel()
}

