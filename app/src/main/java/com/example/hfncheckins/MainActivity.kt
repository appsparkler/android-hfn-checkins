package com.example.hfncheckins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class MainActivity : ComponentActivity() {
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        scanner = scanner
                    )
                }
            }
        }
    }
}

@Composable
fun App(
    scanner:  GmsBarcodeScanner
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