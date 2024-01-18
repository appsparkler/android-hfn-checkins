package com.appsparkler.hfncheckins.ui.components

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.codescanner.LiveBarcodeScanningActivity
import com.appsparkler.hfncheckins.codescanner.Utils
import com.appsparkler.hfncheckins.db.getDb
import com.appsparkler.hfncheckins.ui.components.AbhyasiIdCheckinScreen.AbhyasiIdCheckinScreen
import com.appsparkler.hfncheckins.ui.components.AbhyasiIdCheckinScreen.AbhyasiIdCheckinViewModel
import com.appsparkler.hfncheckins.ui.components.CheckinSuccessScreen.CheckinSuccessScreen
import com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.CheckinWithMobileOrEmailViewModel
import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen.EmailWithMobileOrEmailScreen
import com.appsparkler.hfncheckins.ui.components.MainScreen.MainScreen
import com.appsparkler.hfncheckins.ui.components.QRCheckinScreen.QRCheckinScreen
import com.appsparkler.hfncheckins.ui.components.QRCheckinScreen.QRCheckinScreenViewModel
import com.appsparkler.hfncheckins.model.Routes
import com.appsparkler.hfncheckins.utils.isQRValid
import com.appsparkler.hfncheckins.utils.isValidAbhyasiId
import com.appsparkler.hfncheckins.model.AbhyasiIdCheckin
import com.appsparkler.hfncheckins.model.InputValueType
import com.appsparkler.hfncheckins.model.MobileOrEmailCheckinDBModel
import com.appsparkler.hfncheckins.model.QRCodeCheckinDBModel
import com.appsparkler.hfncheckins.ui.components.MainScreen.EventsManager
import com.appsparkler.hfncheckins.ui.components.MainScreen.EventsViewModel
import com.appsparkler.hfncheckins.ui.components.MainScreen.EventsViewModelV0
import com.appsparkler.hfncheckins.ui.components.MainScreen.MainScreenViewModel
import com.appsparkler.hfncheckins.utils.getQRCheckinsAndMore

@Composable
fun AppWithNav(
  modifier: Modifier = Modifier,
  mainScreenViewModel: MainScreenViewModel = viewModel(),
  eventsViewModel: EventsViewModel = viewModel(),
  navController: NavHostController = rememberNavController(),
  onClickScan: (batch: String?) -> Unit,
  onCheckinWithAbhyasiId: (
    abhyasiIdCheckin: AbhyasiIdCheckin,
  ) -> Unit,
  onCheckinWithEmailOrMobile: (
    emailOrMobileCheckin: MobileOrEmailCheckinDBModel,
  ) -> Unit,
  onCheckinWithQRCode: (
    qrCodeCheckin: QRCodeCheckinDBModel,
  ) -> Unit
) {
  val eventsViewModelState by eventsViewModel.uiState.collectAsState()
  val mainScreenViewModelState by mainScreenViewModel.uiState.collectAsState()
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Routes.MAIN_SCREEN.name
  ) {
    navController.enableOnBackPressed(enabled = false)
    val navigateToSuccessScreen: () -> Unit = {
      navController.popBackStack(Routes.MAIN_SCREEN.name, true)
      navController.navigate(
        Routes.CHECKIN_SUCCESS_SCREEN.name
      )
    }
    val navigateToMainScreen: () -> Unit = {
      navController.popBackStack()
      navController.navigate(
        Routes.MAIN_SCREEN.name
      )
    }
    val handleCancel: () -> Unit = {
      navController.popBackStack(Routes.MAIN_SCREEN.name, true)
      navigateToMainScreen()
      mainScreenViewModel.reset()
    }
    composable(Routes.MAIN_SCREEN.name) {
      MainScreen(
        eventsViewModel = eventsViewModel,
        onStartCheckin = { inputValue, type ->
          when (type) {
            InputValueType.ABHYASI_ID -> {
              navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/$inputValue")
            }

            InputValueType.PHONE_NUMBER -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type")
            }

            InputValueType.EMAIL -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type")
            }
          }
        },
        onClickScan = onClickScan,
        mainScreenViewModel = mainScreenViewModel
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
            batch = mainScreenViewModelState.batch,
            email = if (type == InputValueType.EMAIL.name) emailOrPhoneNumber else "",
            mobile = if (isMobile) emailOrPhoneNumber else "",
            startWithMobile = isMobile,
            event = eventsViewModelState.selectedEvent?.title
          )
          EmailWithMobileOrEmailScreen(
            onClickCheckin = {
              onCheckinWithEmailOrMobile(it)
              navigateToSuccessScreen()
              mainScreenViewModel.reset()
            },
            checkinWithMobileOrEmailViewModel = checkWithEmailOrMobileCheckinViewModel,
            onClickCancel = handleCancel
          )
        }
      }
    }
    composable(
      route = "${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/{code}",
      arguments = listOf(
        navArgument("code") {
          type = NavType.StringType
        }
      )
    ) {
      it.arguments?.getString("code")?.let { code ->
        if (code.isNotEmpty()) {
          val abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
          abhyasiIdCheckinViewModel.update(
            abhyasiId = code.uppercase(),
          )
          AbhyasiIdCheckinScreen(
            abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel,
            onClickCheckin = {
              onCheckinWithAbhyasiId(
                it.copy(
                  timestamp = System.currentTimeMillis()
                )
              )
              navigateToSuccessScreen()
            },
            onClickCancel = handleCancel,
          )
        } else {
          Text("No Abhyasi Id Found!!")
        }
      }
    }
    composable(
      route = "${Routes.QR_CHECKIN_DETAIL_SCREEN}/{code}",
      arguments = listOf(
        navArgument(name = "code") {
          type = NavType.StringType
        }
      )
    ) {
      it.arguments?.getString("code")?.let { code ->
        val qrCheckinViewModel = QRCheckinScreenViewModel()
        val qrCheckins = getQRCheckinsAndMore(code)
        qrCheckinViewModel.setupList(qrCheckins.checkins)
        qrCheckinViewModel.updateMore((qrCheckins.more))
        QRCheckinScreen(
          qrCheckinviewModel = qrCheckinViewModel,
          onClickCheckin = {
            it.forEach(onCheckinWithQRCode)
            navigateToSuccessScreen()
          },
          onClickCancel = handleCancel
        )
      }
    }
    composable(
      route = Routes.CHECKIN_SUCCESS_SCREEN.name
    ) {
      CheckinSuccessScreen(
        onClickReturnToMain = {
          navigateToMainScreen()
        }
      )
    }
  }
}

private const val SCAN_RESULT_KEY = "SCAN_RESULT_KEY"

@Composable
fun AppWithCodeScannerAndRouter(
  modifier: Modifier = Modifier,
  mainScreenViewModel: MainScreenViewModel = viewModel(),
  eventsViewModel: EventsViewModel = viewModel(),
  onCheckinWithAbhyasiId: (abhyasiIdCheckin: AbhyasiIdCheckin) -> Unit,
  onCheckinWithEmailOrMobile: (emailOrMobileCheckin: MobileOrEmailCheckinDBModel) -> Unit,
  onCheckinWithQRCode: (qrCodeCheckin: QRCodeCheckinDBModel) -> Unit
) {
  val navController: NavHostController = rememberNavController()
  val context = LocalContext.current
  if (!Utils.allPermissionsGranted(context)) {
    Utils.requestRuntimePermissions(context as ComponentActivity)
  }

  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
  ) {
    if (it.resultCode == RESULT_OK) {
      val resultData = it.data?.getStringExtra(SCAN_RESULT_KEY).toString()
      if (isValidAbhyasiId(resultData)) {
        navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/$resultData")
      } else if (isQRValid(resultData)) {
        navController.navigate("${Routes.QR_CHECKIN_DETAIL_SCREEN.name}/$resultData")
      } else {
        Toast.makeText(
          context,
          "Invalid QR/Barcode: $resultData",
          Toast.LENGTH_LONG
        )
          .show()
      }
    }
  }
  HFNTheme {
    Scaffold(
      modifier = modifier,
      containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
      val backgroundImage = if (isSystemInDarkTheme()) painterResource(id = R.drawable.bg_dark) else
        painterResource(id = R.drawable.bg_light) // Replace with your image resource
      Image(
        modifier = Modifier
          .fillMaxSize(),
        painter = backgroundImage,
        contentDescription = "background image",
        contentScale = ContentScale.Crop
      )
      AppWithNav(
        modifier = Modifier
          .padding(paddingValues)
          .padding(horizontal = 18.dp),
        mainScreenViewModel = mainScreenViewModel,
        eventsViewModel = eventsViewModel,
        navController = navController,
        onClickScan = {
          launcher.launch(
            Intent(context, LiveBarcodeScanningActivity::class.java)
          )
        },
        onCheckinWithAbhyasiId = onCheckinWithAbhyasiId,
        onCheckinWithEmailOrMobile = onCheckinWithEmailOrMobile,
        onCheckinWithQRCode = onCheckinWithQRCode,
      )
    }
  }
}

val TAG = "AppWithCodeScannerAndRouterAndFirebase"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppWithCodeScannerAndRouterAndFirebase(
  mainScreenViewModel: MainScreenViewModel = viewModel(),
  eventsViewModel: EventsViewModel = viewModel()
) {
  val eventsViewModelState by eventsViewModel.uiState.collectAsState()
  val context = LocalContext.current

  val eventsManager = EventsManager(context)
  eventsManager.setEvents()

  val db = getDb()

  val collection = db.collection("/events/${eventsViewModelState.selectedEvent?.id}/checkins")

  LaunchedEffect(Unit) {
    eventsViewModel.setupEventListener()
  }

  AppWithCodeScannerAndRouter(
    eventsViewModel = eventsViewModel,
    mainScreenViewModel = mainScreenViewModel,
  onCheckinWithAbhyasiId = {
      collection.document("${it.abhyasiId}").set(it)
        .addOnSuccessListener {
          Log.d(TAG, "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener() {
          Log.w(TAG, "Error writing document", it)
        }
    },
    onCheckinWithEmailOrMobile = {
      val collection_ = db.collection("/events/${eventsViewModelState.selectedEvent?.id}/checkins")
      collection_.document("em-${it.email}-${it.mobile}-${it.fullName}").set(it)
        .addOnSuccessListener {
          Log.d(TAG, "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener() {
          Log.w(TAG, "Error writing document", it)
        }
    },
    onCheckinWithQRCode = {
      val collection_ = db.collection("/events/${eventsViewModelState.selectedEvent?.id}/checkins")
      collection_.document("${it.regId}").set(it)
        .addOnSuccessListener {
          Log.d(TAG, "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener() {
          Log.w(TAG, "Error writing document", it)
        }
    }
  )
}
