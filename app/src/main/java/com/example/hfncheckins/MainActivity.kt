package com.example.hfncheckins

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.navigation.NavHostController
import com.example.hfncheckins.codescanner.Utils
import com.example.hfncheckins.ui.components.AppWithCodeScannerAndRouter
import com.example.hfncheckins.ui.components.Routes
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
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
        if(requestCode == REQUEST_CODE_SCAN){
            if(resultCode == Activity.RESULT_OK) {
                val resultData = data?.getStringExtra("SCAN_RESULT_KEY").toString()
                if(isValidAbhyasiId(resultData)) {
                    navController.navigate("${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code=$resultData")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth

        if(auth.currentUser == null){
            auth.signInAnonymously()
        }

        val db = Firebase.firestore
        val context = this

        setContent {
                AppWithCodeScannerAndRouter()
        }
    }
}
