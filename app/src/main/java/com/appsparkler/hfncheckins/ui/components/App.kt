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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
import com.appsparkler.hfncheckins.utils.getDefaultBatch
import com.appsparkler.hfncheckins.utils.getQRCheckinsAndMore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun AppWithNav(
  modifier: Modifier = Modifier,
  hfnEvent: HFNEvent,
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
    val handleCancel:() -> Unit = {
      navController.popBackStack(Routes.MAIN_SCREEN.name, true)
      navigateToMainScreen()
    }
    composable(Routes.MAIN_SCREEN.name) {
      MainScreen(
        hfnEvent = hfnEvent,
        onStartCheckin = { inputValue, type, batch ->
          when (type) {
            InputValueType.ABHYASI_ID -> {
              navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$batch")
            }
            InputValueType.PHONE_NUMBER -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type/$batch")
            }
            InputValueType.EMAIL -> {
              navController.navigate("${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/$inputValue/$type/$batch")
            }
          }
        },
        onClickScan = onClickScan
      )
    }
    composable(
      route = "${Routes.MOBILE_OR_EMAIL_CHECKIN_DETAIL_SCREEN.name}/{emailOrPhoneNumber}/{type}/{batch}",
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
          navBackStackEntry.arguments?.getString("batch").let { batch ->
            val checkWithEmailOrMobileCheckinViewModel =
              CheckinWithMobileOrEmailViewModel()
            val isMobile = type == InputValueType.PHONE_NUMBER.name
            checkWithEmailOrMobileCheckinViewModel.update(
              email = if (type == InputValueType.EMAIL.name) emailOrPhoneNumber else "",
              mobile = if (isMobile) emailOrPhoneNumber else "",
              startWithMobile = isMobile,
              batch = batch,
              event = hfnEvent.title
            )
            EmailWithMobileOrEmailScreen(
              onClickCheckin = {
                onCheckinWithEmailOrMobile(it)
                navigateToSuccessScreen()
              },
              checkinWithMobileOrEmailViewModel = checkWithEmailOrMobileCheckinViewModel,
              onClickCancel = handleCancel
            )
          }
        }
      }
    }
    composable(
      route = "${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/{code}/{batch}",
      arguments = listOf(
        navArgument("code") {
          type = NavType.StringType
        },
        navArgument("batch") {
          type = NavType.StringType
        }
      )
    ) {
      it.arguments?.getString("code")?.let { code ->
        it.arguments?.getString("batch")?.let { batch ->
          if (code.isNotEmpty()) {
            val abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
            abhyasiIdCheckinViewModel.update(
              abhyasiId = code.uppercase(),
              batch = batch,
            )
            AbhyasiIdCheckinScreen(
              abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel,
              onClickCheckin = {
                onCheckinWithAbhyasiId(it.copy(
                  timestamp = System.currentTimeMillis()
                ))
                navigateToSuccessScreen()
              },
              onClickCancel = handleCancel,
            )
          } else {
            Text("No Abhyasi Id Found!!")
          }
        }
      }
    }
    composable(
      route = "${Routes.QR_CHECKIN_DETAIL_SCREEN}/{code}/{batch}",
      arguments = listOf(
        navArgument(name = "code") {
          type = NavType.StringType
        },
        navArgument(name = "batch") {
          type = NavType.StringType
        }
      )
    ) {
      it.arguments?.getString("code")?.let { code ->
        it.arguments?.getString("batch")?.let { batch ->
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
  hfnEvent: HFNEvent,
  onCheckinWithAbhyasiId: (abhyasiIdCheckin: AbhyasiIdCheckin) -> Unit,
  onCheckinWithEmailOrMobile: (emailOrMobileCheckin: MobileOrEmailCheckinDBModel) -> Unit,
  onCheckinWithQRCode: (qrCodeCheckin: QRCodeCheckinDBModel) -> Unit
) {
  var batch: String? = ""
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
        navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}/$resultData/$batch")
      } else if (isQRValid(resultData)) {
        navController.navigate("${Routes.QR_CHECKIN_DETAIL_SCREEN.name}/$resultData/$batch")
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
      val backgroundImage = if(isSystemInDarkTheme()) painterResource(id = R.drawable.bg_dark) else
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
        navController = navController,
        onClickScan = {
          batch = it
          launcher.launch(
            Intent(context, LiveBarcodeScanningActivity::class.java)
          )
        },
        onCheckinWithAbhyasiId = onCheckinWithAbhyasiId,
        onCheckinWithEmailOrMobile = onCheckinWithEmailOrMobile,
        onCheckinWithQRCode = onCheckinWithQRCode,
        hfnEvent = hfnEvent
      )
    }
  }
}


val TAG = "AppWithCodeScannerAndRouterAndFirebase"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppWithCodeScannerAndRouterAndFirebase() {
  val db = Firebase.firestore
  val defaultBatch = getDefaultBatch()
  val hfnEvent = HFNEvent(
    title = "2023 October Retreat",
    id = "20231027_october_retreat",
    batches = listOf(
      "checkin",
      "Day 1",
      "Day 2",
      "Day 3"
    ),
    defaultBatch = defaultBatch
  )
  val collection = db.collection("/events/${hfnEvent.id}/checkins")
  AppWithCodeScannerAndRouter(
    hfnEvent = hfnEvent,
    onCheckinWithAbhyasiId = {
      collection.document("${it.abhyasiId}-${it.batch}").set(it)
        .addOnSuccessListener {
          Log.d(TAG, "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener() {
          Log.w(TAG, "Error writing document", it)
        }
    },
    onCheckinWithEmailOrMobile = {
      collection.document("em-${it.email}-${it.mobile}-${it.fullName}-${it.batch}").set(it)
        .addOnSuccessListener {
          Log.d(TAG, "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener() {
          Log.w(TAG, "Error writing document", it)
        }
    },
    onCheckinWithQRCode = {
//      collection.document("${it.regId}-${it.batch}").set(it)
//        .addOnSuccessListener {
//          Log.d(TAG, "DocumentSnapshot successfully written!")
//        }
//        .addOnFailureListener() {
//          Log.w(TAG, "Error writing document", it)
//        }
    }
  )
}
