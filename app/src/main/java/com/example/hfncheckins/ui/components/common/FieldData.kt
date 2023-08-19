package com.example.hfncheckins.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun FieldData(
  modifier: Modifier = Modifier,
  fieldName: String,
  fieldValue: String? = null
) {
    if(!fieldValue.isNullOrBlank()){
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

@Preview
@Composable
fun FieldDataPreview() {
  HFNTheme {
    Scaffold {
      FieldData(
        modifier = Modifier.padding(it),
        fieldName = "Name: ",
        fieldValue = "John Doe"
      )
    }
  }
}
