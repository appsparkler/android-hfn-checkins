package com.example.hfncheckins.ui.components.QRCheckinScreen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.CustomLazyColumn
import com.example.hfncheckins.ui.components.common.Heading
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import com.example.hfncheckins.model.QRCodeCheckin

@Composable
fun QRCheckinScreen(
    modifier: Modifier = Modifier,
    qrCheckinviewModel: QRCheckinScreenViewModel = viewModel(),
    onClickCheckin: (List<QRCodeCheckin>) -> Unit,
    onClickCancel: () -> Unit,
) {
    val qrCheckins by qrCheckinviewModel.uiState.collectAsState()
    CustomLazyColumn(modifier = modifier) {
        item {
            Heading(
                heading = "Checkin with \n QR"
            )
        }
        items(qrCheckins.size) {
            QRCheckinItem(
                checkinInfo = qrCheckins[it],
                onChange = {
                    qrCheckinviewModel.update(it)
                })
        }
        item {
            CheckinAndCancelButtons(
                isCheckinValid = qrCheckinviewModel.isValid(),
                onClickCancel = onClickCancel,
                onClickCheckin = {
                    onClickCheckin(qrCheckinviewModel.getCheckedInItems())
                }
            )
        }
    }
}

@Preview
@Composable
fun QRCheckinScreenPreview() {
    val qrCheckinScreenViewModel = QRCheckinScreenViewModel()

    val qrCheckinItems = listOf(
        QRCodeCheckin(
            checkin = false,
            orderId = "23433",
            fullName = "James Allen",
            berthPreference = "LB",
            dormPreference = "B1",
            abhyasiId = "INAWIE383",
            eventName = "Bhandara",
            pnr = "INR-APQ-1234",
            regId = "0",
            timestamp = 0,
            dormAndBerthAllocation = "",
            batch = "batch1"
        ),
        QRCodeCheckin(
            checkin = false,
            orderId = "23433",
            fullName = "Warren Buffet",
            berthPreference = "LB",
            dormPreference = "B1",
            abhyasiId = "INAWIE383",
            eventName = "Bhandara",
            pnr = "INR-APQ-1234",
            regId = "1",
            timestamp = 0,
            dormAndBerthAllocation = "",
            batch = "batch1"
        ),
        QRCodeCheckin(
            checkin = false,
            orderId = "23433",
            fullName = "Gaur Gopal Das",
            berthPreference = "LB",
            dormPreference = "B1",
            abhyasiId = "INAWIE383",
            eventName = "Bhandara",
            pnr = "INR-APQ-1234",
            regId = "2",
            timestamp = 0,
            dormAndBerthAllocation = "",
            batch = "batch2"
        )
    )
    qrCheckinScreenViewModel.setupList(qrCheckinItems)
    HFNTheme {
        Scaffold {
            QRCheckinScreen(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp),
                onClickCancel = {},
                onClickCheckin = {
                    val checkedInList = it.filter {
                        it.checkin
                    }
                    Log.d("QRCheckinScreen", checkedInList.size.toString())
                },
                qrCheckinviewModel = qrCheckinScreenViewModel
            )
        }
    }
}