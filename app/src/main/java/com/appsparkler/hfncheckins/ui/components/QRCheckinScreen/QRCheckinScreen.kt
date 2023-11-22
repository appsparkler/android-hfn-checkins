package com.appsparkler.hfncheckins.ui.components.QRCheckinScreen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsparkler.hfncheckins.model.CheckinType
import com.appsparkler.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.appsparkler.hfncheckins.ui.components.common.CustomLazyColumn
import com.appsparkler.hfncheckins.ui.components.common.Heading
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme
import com.appsparkler.hfncheckins.model.QRCodeCheckin
import com.appsparkler.hfncheckins.model.QRCodeCheckinDBModel

@Composable
fun QRCheckinScreen(
    modifier: Modifier = Modifier,
    qrCheckinviewModel: QRCheckinScreenViewModel = viewModel(),
    onClickCheckin: (List<QRCodeCheckinDBModel>) -> Unit,
    onClickCancel: () -> Unit,
) {
    val qrCheckins by qrCheckinviewModel.uiState.collectAsState()
    val more by qrCheckinviewModel.more.collectAsState()
    CustomLazyColumn(modifier = modifier) {
        item {
            Heading(
                heading = "Checkin with \n QR",
            )
        }
        items(qrCheckins.size) {
            QRCheckinItem(
                checkinInfo = qrCheckins[it],
                onChange = {
                    qrCheckinviewModel.update(it)
                })
        }
        if(more.isNotBlank()) {
            item {
                Text(
                    text = "+ $more (please checkin separately)",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
            }
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
    qrCheckinScreenViewModel.updateMore("2 more")

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
//            batch = "batch1",
            type = CheckinType.QR.name
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
//            batch = "batch1",
            type = CheckinType.QR.name
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
//            batch = "batch2",
            type = CheckinType.QR.name

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
                    Log.d("QRCheckinScreen", it.size.toString())
                },
                qrCheckinviewModel = qrCheckinScreenViewModel
            )
        }
    }
}