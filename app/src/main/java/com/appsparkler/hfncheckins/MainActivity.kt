package com.appsparkler.hfncheckins

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.appsparkler.hfncheckins.codescanner.Utils
import com.appsparkler.hfncheckins.model.Routes
import com.appsparkler.hfncheckins.ui.components.AppWithCodeScannerAndRouterAndFirebase
import com.appsparkler.hfncheckins.utils.isValidAbhyasiId
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
  lateinit var navController: NavHostController
  override fun onResume() {
    super.onResume()
    if (!Utils.allPermissionsGranted(this)) {
      Utils.requestRuntimePermissions(this)
    }
  }

  val REQUEST_CODE_SCAN = 1

  @Deprecated("Deprecated in Java")
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE_SCAN) {
      if (resultCode == Activity.RESULT_OK) {
        val resultData = data?.getStringExtra("SCAN_RESULT_KEY").toString()
        if (isValidAbhyasiId(resultData)) {
          navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code=$resultData")
        }
      }
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    val auth = Firebase.auth

    if (auth.currentUser == null) {
      auth.signInAnonymously()
    }

    setContent {
      AppWithCodeScannerAndRouterAndFirebase()
    }
  }
}
