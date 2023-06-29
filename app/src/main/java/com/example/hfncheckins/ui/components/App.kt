package com.example.hfncheckins.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.model.HFNEvent
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.example.hfncheckins.utils.isEmailValid
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import com.example.hfncheckins.viewModel.AppViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class Routes {
    MAIN_SCREEN,
    ABHYASI_CHECKIN_DETAIL_SCREEN,
    MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN,
    QR_CHECKIN_DETAIL_SCREEN
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    HFNTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            val appUiState by appViewModel.uiState.collectAsState()
            val scanner = getGmsBarcodeScanner()
            val event = getSampleEvent()
            NavHost(
                modifier = modifier
                    .padding(12.dp),
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
                                navController,
                                scope,
                                snackbarHostState,
                                false
                            )
                        },
                        onClickScan = {
                            handleClickScan(
                                scanner,
                                event,
                                appViewModel,
                                navController,
                                scope,
                                snackbarHostState,
                            )
                        }
                    )
                }
                composable(Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name) {
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
                composable(Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name) {
                    Column {
                        Text(
                            text = "Checkin with Email Or Mobile",
                            style = MaterialTheme.typography.titleMedium
                        )
                        val isMobileCheckin = appUiState.mobileOrEmailCheckin?.mobile !== ""
                        val emailOrMobileTextValue = if (isMobileCheckin) {
                            appUiState.mobileOrEmailCheckin?.mobile.toString()
                        } else {
                            appUiState.mobileOrEmailCheckin?.email.toString()
                        }
                        Text(
                            text = emailOrMobileTextValue,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                composable(Routes.QR_CHECKIN_DETAIL_SCREEN.name) {
                    Column {
                        Text(
                            text = "Checkin with QR",
                            style = MaterialTheme.typography.titleMedium
                        )
                        appUiState.qrcodeValue?.let { it1 ->
                            Text(
                                text = it1,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

fun showSnackbar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    message: String
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message,
            withDismissAction = true,
            duration = SnackbarDuration.Short,
        )
    }
}

fun handleClickStartCheckin(
    inputValue: String,
    event: HFNEvent,
    appViewModel: AppViewModel,
    navController: NavHostController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    isBarcodeScanned: Boolean
) {
    startAbhyasiidCheckin(
        rawValue = inputValue,
        appViewModel = appViewModel,
        event = event,
        navController = navController,
        scope,
        snackbarHostState,
        isBarcodeScanned
    )
    startMobileCheckin(
        inputValue,
        event,
        appViewModel,
        navController
    )
    startEmailCheckin(
        inputValue,
        event,
        appViewModel,
        navController
    )
}

private fun startMobileCheckin(
    inputValue: String,
    hfnEvent: HFNEvent,
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    if (isValidPhoneNumber(inputValue)) {
        appViewModel.startEmailOrMobileCheckin(
            mobile = inputValue,
            event = hfnEvent
        )
        navController.navigate(Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name)
    }
}

private fun startEmailCheckin(
    inputValue: String,
    hfnEvent: HFNEvent,
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    if (isEmailValid(inputValue)) {
        appViewModel.startEmailOrMobileCheckin(
            email = inputValue,
            event = hfnEvent
        )
        navController.navigate(Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name)
    }
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
    navController: NavHostController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    ) {
    scanner.startScan()
        .addOnSuccessListener {
            handleScanSuccess(
                it, appViewModel,
                event, navController,
                scope, snackbarHostState
            )
        }
        .addOnCanceledListener {
            showSnackbar(
                scope = scope,
                snackbarHostState,
                "Scan cancelled..."
            )
        }
}

private fun handleScanSuccess(
    it: Barcode,
    appViewModel: AppViewModel,
    event: HFNEvent,
    navController: NavHostController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    val rawValue = it.rawValue.toString()
    startAbhyasiidCheckin(rawValue, appViewModel, event, navController, scope, snackbarHostState, it.format == Barcode.FORMAT_CODE_128)
    startQrCheckin(rawValue, navController, appViewModel, it.format == Barcode.FORMAT_QR_CODE)
}

fun startQrCheckin(
    rawValue: String,
    navController: NavHostController,
    appViewModel: AppViewModel,
    isQrCheckin: Boolean
) {
    if (isQrCheckin) {
        appViewModel.startQrCheckin(
            rawValue
        )
        navController.navigate(Routes.QR_CHECKIN_DETAIL_SCREEN.name)
    }
}

private fun startAbhyasiidCheckin(
    rawValue: String,
    appViewModel: AppViewModel,
    event: HFNEvent,
    navController: NavHostController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    isBarcodeScanned: Boolean
) {
    if (isValidAbhyasiId(rawValue)) {
        appViewModel.startAbhyasiCheckin(
            abhyasiId = rawValue,
            event = event
        )
        navController.navigate(Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name)
    }
    else {
        if(isBarcodeScanned) {
            showSnackbar(
                scope,
                snackbarHostState,
                "$rawValue is not a valid abhyasi id."
            )
        }
    }

}

@Preview
@Composable
fun AppPreview() {
    App()
}