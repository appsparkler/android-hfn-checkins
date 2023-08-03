package com.example.hfncheckins.ui.components.QRCheckinScreen

import android.util.Log
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.Heading
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.example.hfncheckins.viewModel.QRCodeCheckinInfo


@Composable
fun CustomLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
        userScrollEnabled = userScrollEnabled,
        flingBehavior = flingBehavior,
        reverseLayout = reverseLayout,
        contentPadding = contentPadding,
        state = state
    )
}

@Composable
fun QRCheckinScreen(
    modifier: Modifier = Modifier,
    qrCheckinItems: List<QRCodeCheckinInfo>,
    onClickCheckin: (List<QRCodeCheckinInfo>) -> Unit,
    onClickCancel: () -> Unit,
) {
    var qrCheckins by remember {
        mutableStateOf(qrCheckinItems)
    }
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
                    qrCheckins = qrCheckins.map { qrCheckinItem ->
                        if (it.regId === qrCheckinItem.regId) it
                        else qrCheckinItem
                    }
                })
        }
        item {
            CheckinAndCancelButtons(
                onClickCancel = onClickCancel,
                onClickCheckin = {
                    onClickCheckin(qrCheckins)
                }
            )
        }
    }
}

@Preview
@Composable
fun QRCheckinScreenPreview() {
    var qrCheckinItems = listOf(
        QRCodeCheckinInfo(
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
            dormAndBerthAllocation = ""
        ),
        QRCodeCheckinInfo(
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
            dormAndBerthAllocation = ""
        ),
        QRCodeCheckinInfo(
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
            dormAndBerthAllocation = ""
        )
    )
    HFNCheckinsTheme {
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
                qrCheckinItems = qrCheckinItems
            )
        }
    }
}