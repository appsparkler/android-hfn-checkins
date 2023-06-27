package com.example.hfncheckins.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.model.HFNEvent
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.viewModel.AppViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

enum class Routes() {
    MAIN_SCREEN,
    AbhyasiCheckin_Detail_Screen
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    val scanner = getGmsBarcodeScanner()
    val event = getSampleEvent()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.MAIN_SCREEN.name
    ) {
        composable(Routes.MAIN_SCREEN.name) {
            MainScreen(
                event = event,
                onStartCheckin = {
                    handleClickStartCheckin(
                        it,
                        event,
                        appViewModel,
                        navController
                    )
                },
                onClickScan = {
                    handleClickScan(
                        scanner,
                        event,
                        appViewModel,
                        navController
                    )
                }
            )
        }
        composable(Routes.AbhyasiCheckin_Detail_Screen.name) {
            Column {
                Text(
                    text = "Checkin with Abhyasi Id",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = appUiState.abhyasiIdCheckin?.abhyasiId.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

fun handleClickStartCheckin(
    inputValue: String,
    event: HFNEvent,
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    startAbhyasiidCheckin(
        rawValue = inputValue,
        event = event,
        appViewModel = appViewModel,
        navController = navController
    )
}

@Composable
private fun getGmsBarcodeScanner(): GmsBarcodeScanner {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_CODE_128
        )
        .allowManualInput()
        .build()
    val context = LocalContext.current
    return GmsBarcodeScanning.getClient(context, options)
}


fun handleClickScan(
    scanner: GmsBarcodeScanner,
    event: HFNEvent,
    appViewModel: AppViewModel,
    navController: NavHostController
): Unit {
    scanner.startScan()
        .addOnSuccessListener {
            handleScanSuccess(
                it, appViewModel,
                event, navController
            )
        }
}

private fun handleScanSuccess(
    it: Barcode,
    appViewModel: AppViewModel,
    event: HFNEvent,
    navController: NavHostController
) {
    val rawValue = it.rawValue.toString()
    startAbhyasiidCheckin(rawValue, appViewModel, event, navController)
}

private fun startAbhyasiidCheckin(
    rawValue: String,
    appViewModel: AppViewModel,
    event: HFNEvent,
    navController: NavHostController
) {
    if (isValidAbhyasiId(rawValue)) {
        appViewModel.startAbhyasiCheckin(
            abhyasiId = rawValue,
            event = event
        )
        navController.navigate(Routes.AbhyasiCheckin_Detail_Screen.name)
    }
}

@Preview(
//    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AppPreview() {
    HFNTheme() {

        Scaffold {
            App(
                modifier = Modifier
                    .padding(it)
                    .padding(12.dp),
                navController = rememberNavController()
            )
        }
    }
}