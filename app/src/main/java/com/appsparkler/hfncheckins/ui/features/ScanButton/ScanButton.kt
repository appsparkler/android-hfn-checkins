package com.appsparkler.hfncheckins.ui.features.ScanButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.R

@Composable
fun ScanButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {}
) {
  ExtendedFloatingActionButton(
    modifier = modifier,
    onClick = onClick
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(text = "Scan")
      Image(
        painter = painterResource(
          id = R.drawable.baseline_qr_code_24
        ),
        contentDescription = null
      )
      Text(text = "or")
      Image(
        painter = painterResource(
          id = R.drawable.barcode
        ),
        contentDescription = null
      )
    }
  }
}

@Preview
@Composable
fun ScanButtonPreview() {
  Scaffold(
    floatingActionButton = { ScanButton() }
  ) {
    Text(
      text = "Hello World",
      modifier = Modifier.padding(it)
    )
  }
}