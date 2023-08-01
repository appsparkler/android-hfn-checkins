package com.example.hfncheckins.ui.components.QRCheckinScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

@Composable
fun FieldData(
    modifier: Modifier = Modifier,
    fieldName: String,
    fieldValue: String
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = fieldName,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = fieldValue,
        )
    }
}

@Composable
fun QRCheckinItem(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = true, onCheckedChange = {})
                Text(
                    text = "Jane Mathew",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            FieldData(
                fieldName = "Abhyasi ID: ",
                fieldValue = "INWWI281"
            )
            FieldData(
                fieldName = "Registration ID: ",
                fieldValue = "b366daa6-2960-4a8b-a300-29bb05ae4e46"
            )
            FieldData(
                fieldName = "Order ID: ",
                fieldValue = "24999"
            )
            FieldData(
                fieldName = "Dorm Preference: ",
                fieldValue = "East Comfort Dorm"
            )
            FieldData(
                fieldName = "Berth Preference: ",
                fieldValue = "LB"
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "", onValueChange = {},
                label = {
                    Text("Dorm and Berth Allocation")
                }
            )
        }
    }
}

@Preview
@Composable
fun QRCheckinPreview() {
    HFNCheckinsTheme {
        Scaffold {
            QRCheckinItem(
                modifier = Modifier.padding(it)
                    .padding(8.dp)
            )
        }
    }
}