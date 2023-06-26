package com.example.hfncheckins.ui.components.MainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.model.HFNEvent
import com.example.hfncheckins.ui.components.InputScreen.ScanButton

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    event: HFNEvent,
    onStartCheckin: (String) -> Unit,
    onClickScan: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = onStartCheckin
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            ScanButton(onClick = onClickScan)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
//    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview() {
    HFNTheme() {
        Scaffold {
            MainScreen(
                modifier = Modifier.padding(it)
                    .padding(12.dp),
                event = getSampleEvent(),
                onStartCheckin = {},
                onClickScan = {}
            )
        }
    }
}