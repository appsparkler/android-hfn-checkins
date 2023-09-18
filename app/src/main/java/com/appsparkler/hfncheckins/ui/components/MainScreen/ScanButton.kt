package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun ScanButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            Modifier
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