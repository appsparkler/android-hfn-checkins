package com.appsparkler.hfncheckins.ui.components.QRCheckinScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.model.CheckinType
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme
import com.appsparkler.hfncheckins.model.QRCodeCheckin
import com.appsparkler.hfncheckins.ui.components.common.FieldData

@Composable
fun QRCheckinItem(
    modifier: Modifier = Modifier,
    checkinInfo: QRCodeCheckin,
    onChange: (QRCodeCheckin) -> Unit
) {
    val cardContainerColor = if (checkinInfo.checkin) MaterialTheme.colorScheme.primaryContainer
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
                    checked = checkinInfo.checkin, onCheckedChange = {
                        onChange(checkinInfo.copy(
                            checkin = it
                        ))
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
                fieldName = "Order ID: ",
                fieldValue = checkinInfo.orderId
            )
            FieldData(fieldName = "PNR: ", fieldValue = checkinInfo.pnr)
            FieldData(
                fieldName = "Registration ID: ",
                fieldValue = checkinInfo.regId
            )
            FieldData(
                fieldName = "Abhyasi ID: ",
                fieldValue = checkinInfo.abhyasiId
            )
            FieldData(
                fieldName = "Batch: ",
                fieldValue = checkinInfo.batch
            )
            FieldData(
                fieldName = "Dorm Preference: ",
                fieldValue = checkinInfo.dormPreference
            )
            FieldData(
                fieldName = "Berth Preference: ",
                fieldValue = checkinInfo.berthPreference
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = checkinInfo.dormAndBerthAllocation,
                enabled = checkinInfo.checkin,
                onValueChange = {
                    onChange(
                        checkinInfo.copy(
                            dormAndBerthAllocation = it
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text("Dorm and Berth Allocation")
                }
            )
        }
    }
}

@Preview
@Composable
fun QRCheckinItemPreview() {
    val context = LocalContext.current

    HFNTheme {
        Scaffold {
            QRCheckinItem(
                modifier = Modifier
                    .padding(it)
                    .padding(8.dp),
                onChange = {
                           Toast.makeText(context, it.checkin.toString(), Toast.LENGTH_SHORT)
                               .show()
                },
                checkinInfo = QRCodeCheckin(
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
                    checkin = true,
                    batch = "batch1",
                    type = CheckinType.QR.name
                )
            )
        }
    }
}