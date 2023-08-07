package com.example.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

val ageOptions = listOf(
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

@Composable
fun AgeAndGenderRow(
    modifier: Modifier = Modifier,
    age: String,
    onChangeAge: (String) -> Unit,
    gender: String,
    onChangeGender: (String) -> Unit
) {
    Row(
        modifier = modifier.padding(),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        SelectField(
            options = ageOptions,
            modifier = Modifier
                .weight(1f),
            label = "Age",
            value = age,
            onChange = onChangeAge
        )
        SelectField(
            options = listOf("Female", "Male", "Unspecified"),
            modifier = Modifier
                .weight(1f),
            label = "Gender",
            value = gender,
            onChange = onChangeGender
        )
    }
}

@Preview
@Composable
fun AgeAndGenderRowPreview() {
    HFNCheckinsTheme() {
        Scaffold {
            AgeAndGenderRow(
                modifier = Modifier
                    .padding(it)
                    .padding(12.dp),
                age = "45-49",
                gender = "Female",
                onChangeAge = {
                    Log.d("AgeAndGenderRow", it)
                },
                onChangeGender = {
                    Log.d("AgeAndGenderRow", it)
                }
            )
        }
    }
}
