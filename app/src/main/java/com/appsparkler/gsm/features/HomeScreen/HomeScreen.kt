package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.SuccessScreen.SuccessScreenModel
import com.appsparkler.gsm.features.nav.AppRoutes
import com.appsparkler.gsm.model.QRUser

fun NavHostController.navigateToHome(popUpToRoute: String? = null) {
  navigate(AppRoutes.HOME.name) {
    if(popUpToRoute != null) {
      popUpTo(popUpToRoute) {
        inclusive = true
      }
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
    LaunchedEffect(key1 = Unit) {
      successScreenModel.reset()
      vm.resetState()
    }
    HomeScreenWithScanner(
      modifier = modifier,
      vm = vm,
      onClickCheckin = {
        successScreenModel.setQRUser(null)
        val user = vm.getManualEntryUser()
        vm.addManualEntryRecord(user)
        successScreenModel.setAManualEntryUser(user)
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


