package com.example.hfncheckins.ui.components.InputScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.R
import com.example.hfncheckins.hfnTheme.HFNTheme

@Composable
fun ScanButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier
            .padding(24.dp),
        onClick = onClick
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
//    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ScanButtonPreview() {
    HFNTheme() {
        Scaffold { paddingValues ->
            ScanButton(
                modifier = Modifier.padding(paddingValues),
                onClick = {}
            )
        }
    }
}