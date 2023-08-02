package com.example.hfncheckins.ui.components.QRCheckinScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.example.hfncheckins.viewModel.QRCodeCheckinInfo

@Composable
fun QRCheckinScreen(
    modifier: Modifier = Modifier,
    qrCheckinItems: List<QRCodeCheckinInfo>
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
        )
    ) {
        LazyColumn(
            verticalArrangement = Arrangement
                .spacedBy(
                    16.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Checkin with \n QR",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            items(qrCheckinItems.size) {
                QRCheckinItem(checkinInfo = qrCheckinItems[it], onChange = {})
            }
            item {
                Row {
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text="Cancel")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Checkin")
                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun QRCheckinScreenPreview() {
    HFNCheckinsTheme {
        Scaffold {
            QRCheckinScreen(
                modifier = Modifier
                    .padding(it)
                    .padding(8.dp),
                qrCheckinItems = listOf(
                    QRCodeCheckinInfo(
                        checkin = false,
                        orderId = "23433",
                        fullName = "James Allen",
                        berthPreference = "LB",
                        dormPreference = "B1",
                        abhyasiId = "INAWIE383",
                        eventName = "",
                        pnr = "",
                        regId = "",
                        timestamp = 0,
                        dormAndBerthAllocation = ""
                    ),
                    QRCodeCheckinInfo(
                        checkin = false,
                        orderId = "23433",
                        fullName = "Warren Buffet",
                        berthPreference = "LB",
                        dormPreference = "B1",
                        abhyasiId = "INAWIE383",
                        eventName = "",
                        pnr = "",
                        regId = "",
                        timestamp = 0,
                        dormAndBerthAllocation = ""
                    )
                )
            )
        }
    }
}