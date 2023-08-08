package com.example.hfncheckins.ui.components

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hfncheckins.codescanner.LiveBarcodeScanningActivity
import com.example.hfncheckins.codescanner.Utils
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen.AbhyasiIdCheckinScreen
import com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen.AbhyasiIdCheckinViewModel
import com.example.hfncheckins.ui.components.CheckinSuccessScreen.CheckinSuccessScreen
import com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.CheckinWithMobileOrEmailViewModel
import com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.EmailOrMobileCheckin
import com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.EmailWithMobileOrEmailScreen
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin
import com.example.hfncheckins.viewModel.InputValueType
import com.example.hfncheckins.viewModel.QRCodeCheckin

@Composable
fun App(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  onClickScan: () -> Unit,
  onCheckinWithAbhyasiId: (
    abhyasiIdCheckin: AbhyasiIdCheckin,
  ) -> Unit,
  onCheckinWithEmailOrMobile: (
    emailOrMobileCheckin: EmailOrMobileCheckin,
  ) -> Unit,
  onCheckinWithQRCode: (
    qrCodeCheckin: QRCodeCheckin,
  ) -> Unit
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Routes.MAIN_SCREEN.name
  ) {
    navController.enableOnBackPressed(enabled = false)
    val navigateToSuccessScreen:() -> Unit = {
      navController.navigate(
        Routes.CHECKIN_SUCCESS_SCREEN.name
      )
    }
    val navigateToMainScreen:() -> Unit = {
      navController.navigate(
        Routes.MAIN_SCREEN.name
      )
    }
    composable(Routes.MAIN_SCREEN.name) {
      MainScreen(
        event = getSampleEvent(),
        onStartCheckin = { inputValue, type ->
          when (type) {
            InputValueType.ABHYASI_ID -> {
              navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code=$inputValue")
            }

            InputValueType.PHONE_NUMBER -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type")
            }

            InputValueType.EMAIL -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type")
            }
          }
        },
        onClickScan = onClickScan
      )
    }
    composable(
      route = "${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/{emailOrPhoneNumber}/{type}",
      arguments = listOf(
        navArgument(name = "emailOrPhoneNumber") {
          type = NavType.StringType
        },
        navArgument(name = "type") {
          type = NavType.StringType
        }
      )
    ) { navBackStackEntry ->
      navBackStackEntry.arguments?.getString("emailOrPhoneNumber")?.let { emailOrPhoneNumber ->
        navBackStackEntry.arguments?.getString("type")?.let { type ->
          val checkWithEmailOrMobileCheckinViewModel =
            CheckinWithMobileOrEmailViewModel()
          val isMobile = type == InputValueType.PHONE_NUMBER.name
          checkWithEmailOrMobileCheckinViewModel.update(
            email = if(type == InputValueType.EMAIL.name) emailOrPhoneNumber else "",
            mobile = if(isMobile) emailOrPhoneNumber else "",
            startWithMobile = isMobile
          )
          EmailWithMobileOrEmailScreen(
            onClickCheckin = {
              onCheckinWithEmailOrMobile(it)
              navigateToSuccessScreen()
            },
            checkinWithMobileOrEmailViewModel = checkWithEmailOrMobileCheckinViewModel,
            onClickCancel = {
              navigateToMainScreen()
            }
          )
        }
      }
    }
    composable(
      route = "${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code={code}",
      arguments = listOf(
        navArgument("code") {
          type = NavType.StringType
          defaultValue = "Default"
        }
      )
    ) {
      it.arguments?.getString("code")?.let {
        if (it.isNotEmpty()) {
          val abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
          abhyasiIdCheckinViewModel.update(
            abhyasiId = it,
          )
          AbhyasiIdCheckinScreen(
            abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel,
            onClickCheckin = {
              onCheckinWithAbhyasiId(it)
              navigateToSuccessScreen()
            },
            onClickCancel = {
              navigateToMainScreen()
            },
          )
        } else {
          Text("No Abhyasi Id Found!!")
        }
      }
    }
    composable(
      route = Routes.CHECKIN_SUCCESS_SCREEN.name
    ) {
      CheckinSuccessScreen(
        onClickReturnToMain = {
          navController.navigate(Routes.MAIN_SCREEN.name)
        }
      )
    }
  }
}

private const val SCAN_RESULT_KEY = "SCAN_RESULT_KEY"

@Preview
@Composable
fun AppPreview() {
  var navController: NavHostController = rememberNavController()
  val context = LocalContext.current
  if (!Utils.allPermissionsGranted(context)) {
    Utils.requestRuntimePermissions(context as ComponentActivity)
  }
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
  ) {
    if (it.resultCode == RESULT_OK) {
      val resultData = it.data?.getStringExtra(SCAN_RESULT_KEY).toString()
      resultData
      if (isValidAbhyasiId(resultData)) {
        navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code=$resultData")
      } else if (isValidQRCode(resultData)) {
        navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}?code=$resultData")
      }
    }
  }
//+917337373432
  HFNTheme {
    Scaffold(
      containerColor = MaterialTheme.colorScheme.background
    ) {paddingValues ->
      App(
        modifier = Modifier
          .padding(paddingValues)
          .padding(horizontal =  18.dp),
        navController = navController,
        onClickScan = {
          launcher.launch(
            Intent(context, LiveBarcodeScanningActivity::class.java)
          )
        },
        onCheckinWithAbhyasiId = {
          it
        },
        onCheckinWithEmailOrMobile = {
          it
        },
        onCheckinWithQRCode = {
          it
        }
      )
    }
  }
}

fun isValidQRCode(resultData: String): Boolean {
  return true
}
