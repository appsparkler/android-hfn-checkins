package com.example.hfncheckins

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.ui.components.App
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth

        if(auth.currentUser == null){
            auth.signInAnonymously()
        }

        val db = Firebase.firestore

        setContent {
            HFNTheme {
                Scaffold {
                    Button(
                        modifier =Modifier.padding(it),
                        onClick = {

                            val city = hashMapOf(
                                "name" to "Los Angeles",
                                "state" to "CA",
                                "country" to "USA",
                            )

                            db.collection("cities").document("LA")
                                .set(city)
                                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                        }) {
                        Text("Click")
                    }
//                    App(
//                        modifier = Modifier.padding(it)
//                    )
                }
            }
        }
    }
}
