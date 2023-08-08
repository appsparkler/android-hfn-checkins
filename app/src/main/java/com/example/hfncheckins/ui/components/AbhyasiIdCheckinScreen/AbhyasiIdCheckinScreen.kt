package com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hfncheckins.ui.components.common.CheckinAndCancelButtons
import com.example.hfncheckins.ui.components.common.VerticalSpacer12Dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin

@Composable
fun AbhyasiIdCheckinScreen(
    abhyasiIdCheckinViewModel: AbhyasiIdCheckinViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onClickCheckin: (
        abhyasiIdCheckin: AbhyasiIdCheckin
    ) -> Unit,
    onClickCancel: () -> Unit,
) {
    val abhyasiIdCheckin by abhyasiIdCheckinViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer12Dp()
        Text(
            modifier = Modifier,
            text = "Checkin With \n Abhyasi ID",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement
                        .spacedBy(4.dp)
                ) {
                    Text(
                        text = "Abhyasi Id:",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = abhyasiIdCheckin.abhyasiId)
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = abhyasiIdCheckin.dormAndBerthAllocation,
                    onValueChange = {
                        abhyasiIdCheckinViewModel.update(
                            dormAndBerthAllocation = it
                        )
                    },
                    label = {
                        Text(text = "Dorm and Berth Allocation:")
                    }
                )
                CheckinAndCancelButtons(
                    isCheckinValid = true,
                    onClickCancel = onClickCancel,
                    onClickCheckin = {
                        onClickCheckin(
                            abhyasiIdCheckin
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AbhyasiIdCheckinScreenPreview() {
    val abhyasiIdCheckinViewModel = AbhyasiIdCheckinViewModel()
    abhyasiIdCheckinViewModel.update(
        abhyasiId = "INUEQS228",
    )
    HFNCheckinsTheme {
        Scaffold {
            AbhyasiIdCheckinScreen(
                modifier = Modifier.padding(it),
                onClickCheckin = {

                },
                onClickCancel = {},
                abhyasiIdCheckinViewModel = abhyasiIdCheckinViewModel
            )
        }
    }
}