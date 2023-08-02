package com.example.hfncheckins.ui.components.QRCheckinScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme
import com.example.hfncheckins.viewModel.QRCodeCheckinInfo

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
    modifier: Modifier = Modifier,
    checkinInfo: QRCodeCheckinInfo,
    onChange: (QRCodeCheckinInfo) -> Unit
) {
    var dormAndBerthAllocation by remember {
        mutableStateOf("")
    }
    var isChecked by remember {
        mutableStateOf(false)
    }
    onChange(checkinInfo.copy(
        dormAndBerthAllocation = dormAndBerthAllocation,
        checkin = isChecked
    ))
    val cardContainerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surfaceContainer
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cardContainerColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked, onCheckedChange = {
                    isChecked = it
                })
                Text(
                    text = checkinInfo.fullName,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            FieldData(
                fieldName = "Event Name: ",
                fieldValue = checkinInfo.eventName
            )
            FieldData(
                fieldName = "Abhyasi ID: ",
                fieldValue = checkinInfo.abhyasiId
            )
            FieldData(
                fieldName = "Registration ID: ",
                fieldValue = checkinInfo.regId
            )
            FieldData(
                fieldName = "Order ID: ",
                fieldValue = checkinInfo.orderId
            )
            FieldData(
                fieldName = "Dorm Preference: ",
                fieldValue = checkinInfo.dormPreference
            )
            FieldData(
                fieldName = "Berth Preference: ",
                fieldValue = checkinInfo.berthPreference
            )
            FieldData(fieldName = "PNR: ", fieldValue = checkinInfo.pnr)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dormAndBerthAllocation,
                enabled = isChecked,
                onValueChange = {
                    dormAndBerthAllocation = it
                },
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
    val context = LocalContext.current

    HFNCheckinsTheme {
        Scaffold {
            QRCheckinItem(
                modifier = Modifier
                    .padding(it)
                    .padding(8.dp),
                onChange = {
                           Toast.makeText(context, it.checkin.toString(), Toast.LENGTH_SHORT)
                               .show()
                },
                checkinInfo = QRCodeCheckinInfo(
                    abhyasiId = "INWWI281",
                    berthPreference = "LB",
                    dormPreference = "East Comfort Dorm",
                    dormAndBerthAllocation = "",
                    timestamp = 0,
                    fullName = "Jane Mathew",
                    orderId = "24999",
                    regId = "b366daa6-2960-4a8b-a300-29bb05ae4e46",
                    pnr = "AE-IDDK-IWQ",
                    eventName = "2023 Birth Anniversary Celebrations of Pujya Daaji",
                    checkin = true
                )
            )
        }
    }
}