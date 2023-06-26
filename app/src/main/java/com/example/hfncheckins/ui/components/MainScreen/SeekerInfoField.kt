package com.example.hfncheckins.ui.components.MainScreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.data.strings
import com.example.hfncheckins.hfnTheme.HFNTheme
import com.example.hfncheckins.model.Event
import com.example.hfncheckins.viewModel.SeekerInfoFieldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeekerInfoField(
    modifier: Modifier = Modifier,
    seekerInfoFieldViewModel: SeekerInfoFieldViewModel = viewModel(),
    event: Event,
    onStartCheckin: (String) -> Unit
) {
    val seekerInfoUiState by seekerInfoFieldViewModel.uiState.collectAsState()
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = event.title,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Column {
                Text(
                    text = strings.inputInstructions,
                    style = MaterialTheme.typography.labelSmall
                )
                OutlinedTextField(
                    modifier = Modifier
                        .testTag("seeker-input-field"),
                    value = seekerInfoUiState.value,
                    onValueChange = {
                        seekerInfoFieldViewModel.updateValue(it)
                    },
                    label = {
                        Text(text = strings.info)
                    }
                )
            }
            ElevatedButton(
                onClick = {
                    onStartCheckin(seekerInfoUiState.value)
                },
                enabled = seekerInfoUiState.isValid
            ) {
                Text(text = strings.startCheckin)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun SeekerInfoFieldPreview() {
    HFNTheme() {
        Scaffold {
            SeekerInfoField(
                modifier = Modifier
                    .padding(it)
                    .padding(12.dp),
                event = getSampleEvent(),
                onStartCheckin = {}
            )
        }
    }
}
