package com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun SelectField(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    onChange: (String)-> Unit,
    value: String,
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
            enabled = false,
            modifier = Modifier.clickable {
                menuExpanded = !menuExpanded
            },
            onValueChange = {},
            label = {
                Text(text = label)
            },
            trailingIcon = {
                if (menuExpanded) {
                    IconButton(onClick = {
                        menuExpanded = false
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                            contentDescription = "Close menu"
                        )
                    }
                } else {
                    IconButton(onClick = {
                        menuExpanded = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                            contentDescription = "Open menu"
                        )
                    }
                }
            },
            maxLines = 1
        )
        if (menuExpanded) {
            val customModifier = if (options.size > 5) {
                Modifier.height(235.dp)
            } else {
                Modifier
            }
            DropdownMenu(
                modifier = customModifier,
                expanded = menuExpanded,
                scrollState = scrollState,
                onDismissRequest = {
                    menuExpanded = false
                },
                offset = DpOffset(x = 0.dp, y = 0.dp)
            ) {
                options.map {
                    val background = if(value == it) {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                    DropdownMenuItem(
                        modifier = Modifier.background(background),
                        text = {
                            Text(text = it)
                        },
                        onClick = {
                            menuExpanded = false
                            onChange(it)
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun SelectFieldPreview() {
    var age by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    val menuItems = listOf(
        "0-4",
        "5-9",
        "10-14",
        "15-19",
        "20-24",
        "25-29",
        "30-34",
        "35-39",
        "40-44",
        "45-49",
        "50-54",
        "55-59",
        "60-64",
        "65-69",
        "70-74",
        "75-79",
        "80-84",
        "85-89",
        "90-94",
        "95-99",
        "100+"
    )
    HFNTheme {
        Scaffold { padding ->
            ElevatedCard(
                modifier = Modifier
                    .padding(padding)
                    .padding(12.dp),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "The Form", style = MaterialTheme.typography.titleLarge)
                    OutlinedTextField(
                        value = "", onValueChange = {}, label = {
                        Text(text = "Full Name")
                    }, modifier = Modifier.fillMaxWidth())
                    Row(
                        modifier = Modifier.padding(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp
                        )
                    ) {
                        SelectField(
                            options = menuItems,
                            modifier = Modifier
                                .padding(padding)
                                .weight(1f),
                            label = "Age",
                            value=age,
                            onChange = {
                                age = it
                            }
                        )
                        SelectField(
                            options = listOf("Female", "Male", "Unspecified"),
                            modifier = Modifier
                                .padding(padding)
                                .weight(1f),
                            label = "Gender",
                            value = gender,
                            onChange = {
                                gender = it
                            }
                        )
                    }
                }
            }
        }
    }
}