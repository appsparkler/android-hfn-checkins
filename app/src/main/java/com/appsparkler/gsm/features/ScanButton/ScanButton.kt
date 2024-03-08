package com.appsparkler.gsm.features.ScanButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
    Row {
      Image(
        painter = painterResource(id = R.drawable.baseline_qr_code_24),
        contentDescription = null
      )
    }
  }
}

@Preview
@Composable
fun ScanButtonPreview() {
  ScanButton()
}