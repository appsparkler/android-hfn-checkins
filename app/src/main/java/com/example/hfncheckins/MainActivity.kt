package com.example.hfncheckins

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.codescanner.LiveBarcodeScanningActivity
import com.example.hfncheckins.codescanner.Utils
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        if (!Utils.allPermissionsGranted(this)) {
            Utils.requestRuntimePermissions(this)
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
            val startCodeScannerActivity = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                // Handle the result, if needed
            }
            HFNTheme {
                Scaffold {
                    MainScreen(
                        modifier = Modifier
                            .padding(it)
                            .padding(12.dp),
                        event = getSampleEvent(),
                        onStartCheckin = {},
                        onClickScan =  {
                            val intent = Intent(context, LiveBarcodeScanningActivity::class.java)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}
