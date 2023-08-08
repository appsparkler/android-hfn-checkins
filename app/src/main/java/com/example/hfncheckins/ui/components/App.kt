package com.example.hfncheckins.ui.components

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.EmailOrMobileCheckin
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin
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
    HFNTheme {
        Scaffold(
            modifier = modifier.padding(8.dp)
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Routes.MAIN_SCREEN.name
            ) {
                navController.enableOnBackPressed(enabled = false)
                composable(Routes.MAIN_SCREEN.name) {
                    MainScreen(
                        event = getSampleEvent(),
                        onStartCheckin = { inputValue, type ->

                        },
                        onClickScan = onClickScan
                    )
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
                            var abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
                            abhyasiIdCheckinViewModel.update(
                                abhyasiId = it,
                            )
                            AbhyasiIdCheckinScreen(
                                abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel,
                                onClickCheckin = {
                                    navController.navigate(
                                        Routes.CHECKIN_SUCCESS_SCREEN.name
                                    )
                                    onCheckinWithAbhyasiId(it)
                                },
                                onClickCancel = {
                                    navController.navigate(
                                        Routes.MAIN_SCREEN.name
                                    )
                                },
                            )
                        } else {
                            Text("Something else")
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
    }
}

private const val SCAN_RESULT_KEY = "SCAN_RESULT_KEY"

@Preview
@Composable
fun AppPreview() {
    var navController:NavHostController = rememberNavController()
    val context = LocalContext.current
    if (!Utils.allPermissionsGranted(context)) {
        Utils.requestRuntimePermissions(context as ComponentActivity)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        if(it.resultCode == RESULT_OK) {
            val resultData = it.data?.getStringExtra(SCAN_RESULT_KEY).toString()
            if(isValidAbhyasiId(resultData)) {
                navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code=$resultData")
            }
        }
    }
    Scaffold {
        App(
            modifier = Modifier
                .padding(it)
                .padding(12.dp),
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

            },
            onCheckinWithQRCode = {

            }
        )
    }
}