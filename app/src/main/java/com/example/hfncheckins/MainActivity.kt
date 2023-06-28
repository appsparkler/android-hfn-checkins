package com.example.hfncheckins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.ui.components.App
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import java.util.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_CODE_128
            )
            .build()
        val scanner = GmsBarcodeScanning.getClient(this, options)
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
