package com.appsparkler.gsm.features.HomeScreen

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes
import com.appsparkler.hfncheckins.codescanner.LiveBarcodeScanningActivity

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
    val launcher = rememberLauncherForActivityResult(
      contract = ActivityResultContracts.StartActivityForResult(),
    ) {
      val result = it.data?.getStringExtra("SCAN_RESULT_KEY").toString()
      Toast.makeText(context, "Scanned ${result}", Toast.LENGTH_SHORT).show()
    }
    HomeScreenView(
      modifier = modifier,
      vm = vm,
      onClickCheckin = onClickCheckin,
      onClickScan = {
        launcher.launch(
          Intent(context, LiveBarcodeScanningActivity::class.java)
        )
      }
    )
  }
}

