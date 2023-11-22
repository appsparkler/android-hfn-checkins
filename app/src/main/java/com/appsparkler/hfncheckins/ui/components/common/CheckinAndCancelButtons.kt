package com.appsparkler.hfncheckins.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckinAndCancelButtons(
    modifier: Modifier = Modifier,
    isCheckinValid: Boolean,
    onClickCancel: () -> Unit,
    onClickCheckin: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        OutlinedButton(
            onClick = onClickCancel,
            modifier = Modifier,
            colors =  ButtonDefaults
                .filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                )
        ) {
            Text(text = "Cancel")
        }
        FilledTonalButton(
            onClick = onClickCheckin,
            enabled = isCheckinValid,
            colors =  ButtonDefaults
                .filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = "Checkin",
            )
        }

    }
}