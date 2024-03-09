package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.SuccessScreen.SuccessScreenModel
import com.appsparkler.gsm.features.nav.AppRoutes
import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser

fun NavHostController.navigateToHome() {
  navigate(AppRoutes.HOME.name) {
    popUpTo(AppRoutes.HOME.name) {
      inclusive = false
    }
  }
}

fun NavGraphBuilder.homeScreen(
  modifier: Modifier = Modifier,
  vm: HomeScreenViewModel,
  successScreenModel: SuccessScreenModel = SuccessScreenModel(),
  onClickCheckin: () -> Unit = {},
  onCheckinScan: (QRUser) -> Unit = {}
) {
  composable(route = AppRoutes.HOME.name) {
    vm.resetState()
    val homeScreenViewModelState by vm.state.collectAsState()
    HomeScreenWithScanner(
      modifier = modifier,
      vm = vm,
      onClickCheckin = {
        successScreenModel.setQRUser(null)
        successScreenModel.setAManualEntryUser(
          ManualEntryUser(
            name = homeScreenViewModelState.name,
            mobileNo = homeScreenViewModelState.mobileNo,
            email = homeScreenViewModelState.email,
            organization = homeScreenViewModelState.organization
          )
        )
        onClickCheckin()
      },
      onCheckinScan = {
        successScreenModel.setQRUser(it)
        successScreenModel.setAManualEntryUser(null)
        onCheckinScan(it)
      }
    )
  }
}


