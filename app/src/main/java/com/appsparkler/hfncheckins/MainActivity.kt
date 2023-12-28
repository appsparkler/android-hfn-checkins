package com.appsparkler.hfncheckins

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.appsparkler.hfncheckins.codescanner.Utils
import com.appsparkler.hfncheckins.ui.components.AppWithCodeScannerAndRouterAndFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException

val TAG = "MainActivity"

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

    fetchOngoingEvents().addOnSuccessListener {
      if(!it.isEmpty()) {
        storeInFile(it)
      }
    }
    setContent {
      AppWithCodeScannerAndRouterAndFirebase()
    }
  }

  private fun fetchOngoingEvents(): Task<QuerySnapshot> {
    val db = Firebase.firestore
    return db.collection("ongoing-events").get()
  }

  private fun storeInFile(it: QuerySnapshot) {
    val json = JSONObject()
    it.forEach {
      json.put(it.id, it.data)
    }
    try {
      val file = File(filesDir, "ongoing_events.json")
      val fileWriter = FileWriter(file)
      fileWriter.write(json.toString())
      fileWriter.close()
      Toast.makeText(this, "Latest ongoing events stored in file", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
      Log.e(TAG, "Error writing to file", e)
      // Handle error
    }
  }
}
