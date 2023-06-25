package com.example.hfncheckins.ui.components.InputScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.R
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.model.Event
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    scanner: GmsBarcodeScanner,
    event: Event,
    onValidStart: (String) -> Unit,
    onValidScan: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterVertically
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = event.title,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Column {
                Text(
                    text = "Enter your abhyasi id, mobile # or email address",
                    style = MaterialTheme.typography.labelSmall
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = handleInputValueChange(onValidStart)
                )
            }

            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Start Checkin")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(24.dp),
                onClick = handleClickScan(scanner, onValidScan)
            ) {
                Row(
                    modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {
                    Text(text = "SCAN")
                    Icon(
                        painter = painterResource(id = R.drawable.barcode),
                        contentDescription = "Scan QR code or barcode"
                    )
                    Text(text = "OR")
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_qr_code_24),
                        contentDescription = "Scan QR code or barcode"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun InputScreenPreview() {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_CODE_128
        )
        .build()
    val scanner = GmsBarcodeScanning.getClient(LocalContext.current, options)
    HFNTheme {
        Scaffold {
            InputScreen(
                modifier = Modifier.padding(it),
                event = getSampleEvent(),
                scanner = scanner,
                onValidStart = {},
                onValidScan = {},
            )
        }
    }
}

