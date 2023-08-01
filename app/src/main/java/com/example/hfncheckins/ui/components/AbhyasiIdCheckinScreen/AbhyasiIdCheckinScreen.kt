package com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

@Composable
fun AbhyasiIdCheckinScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(top=100.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = "Checkin With \n Abhyasi ID",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        ElevatedCard(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier= Modifier
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
                    Text(text = "INAAAE338")
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "", onValueChange = {},
                    label = {
                        Text(text = "Dorm and Berth Allocation:")
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(8.dp, Alignment.CenterHorizontally)
                ) {
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = { /*TODO*/ }) {
                        Text(text = "Checkin")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun AbhyasiIdCheckinScreenPreview() {
    HFNCheckinsTheme {
        Scaffold {
            AbhyasiIdCheckinScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}