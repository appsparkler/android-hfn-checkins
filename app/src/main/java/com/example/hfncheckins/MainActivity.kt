package com.example.hfncheckins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.ui.components.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HFNTheme {
                Scaffold {
                        App()
                }
            }
        }
    }
}
