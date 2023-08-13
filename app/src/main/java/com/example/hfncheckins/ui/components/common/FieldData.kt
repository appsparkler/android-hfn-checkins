package com.example.hfncheckins.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FieldData(
  modifier: Modifier = Modifier,
  fieldName: String,
  fieldValue: String
) {
    if(fieldValue.isNotBlank()) {
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
}