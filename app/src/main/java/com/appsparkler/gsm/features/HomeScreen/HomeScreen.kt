package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes
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
  onClickCheckin: () -> Unit = {},
  onCheckinScan: (QRUser) -> Unit = {}
) {
  composable(route = AppRoutes.HOME.name) {
    vm.resetState()

    HomeScreenWithScanner(
      modifier = modifier,
      vm = vm,
      onClickCheckin = onClickCheckin,
      onCheckinScan = onCheckinScan
    )
  }
}


