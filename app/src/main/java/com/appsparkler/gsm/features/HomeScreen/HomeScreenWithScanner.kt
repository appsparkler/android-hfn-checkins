package com.appsparkler.gsm.features.HomeScreen

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.appsparkler.hfncheckins.codescanner.LiveBarcodeScanningActivity

@Composable
fun HomeScreenWithScanner(
  modifier: Modifier = Modifier,
  vm: HomeScreenViewModel,
  onClickCheckin: () -> Unit = {},
  onScan: (String) -> Unit = {}
) {

  val context = LocalContext.current
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
  ) {
    val result = it.data?.getStringExtra("SCAN_RESULT_KEY").toString()
    onScan(result)
  }
  HomeScreenWithScanButton(
    modifier = modifier,
    vm = vm,
    onClickCheckin = onClickCheckin,
    onClickScan = {
      launcher.launch(
        Intent(context, LiveBarcodeScanningActivity::class.java)
      )
    }
  )
}