package com.appsparkler.gsm.features.HomeScreen

import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
  onCheckin: (QRUser) -> Unit = {}
) {

  composable(route = AppRoutes.HOME.name) {
    vm.resetState()
    val context = LocalContext.current

    HomeScreenWithScanner(
      modifier = modifier,
      vm = vm,
      onClickCheckin = onClickCheckin,
      onScan = {
        if (vm.isValidQR(it)) {
          val qrUser: QRUser = vm.addQRRecord()
          onCheckin(qrUser)
        } else {
          Toast.makeText(context, "Scanned: $it", Toast.LENGTH_SHORT).show()
        }
      }
    )
  }
}

