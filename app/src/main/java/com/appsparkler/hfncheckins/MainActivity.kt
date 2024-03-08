package com.appsparkler.hfncheckins

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.appsparkler.gsm.features.nav.App
import com.appsparkler.hfncheckins.codescanner.Utils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
  override fun onResume() {
    super.onResume()
    if (!Utils.allPermissionsGranted(this)) {
      Utils.requestRuntimePermissions(this)
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
      App()
    }
  }
}
