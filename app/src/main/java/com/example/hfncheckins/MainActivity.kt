package com.example.hfncheckins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.model.Event
import com.example.hfncheckins.ui.components.InputScreen.InputScreen
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
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
                    InputScreen(
                        modifier = Modifier.padding(it),
                        scanner = scanner,
                        event = getSampleEvent(),
                        onValidStart = {},
                        onValidScan = {}
                    )
                }
            }
        }
    }
}

@Composable
fun App(
    scanner: GmsBarcodeScanner
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    // Task completed successfully
                }
                .addOnCanceledListener {
                    // Task canceled
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                }
        }) {
            Text(text = "SCAN")
        }
    }
}