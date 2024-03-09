package com.appsparkler.gsm.features.HomeScreen

import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes

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
  onClickCheckin: () -> Unit = {}
) {

  composable(route = AppRoutes.HOME.name) {
    vm.resetState()
    val context = LocalContext.current

    HomeScreenWithScanner(
      modifier = modifier,
      vm = vm,
      onClickCheckin = onClickCheckin,
      onScan = {
        Toast.makeText(context, "Scanned: $it", Toast.LENGTH_SHORT).show()
      }
    )
  }
}

