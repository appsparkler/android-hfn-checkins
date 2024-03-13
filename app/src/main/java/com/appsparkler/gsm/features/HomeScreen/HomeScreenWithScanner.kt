package com.appsparkler.gsm.features.HomeScreen

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsparkler.gsm.model.QRUser
import com.appsparkler.hfncheckins.codescanner.LiveBarcodeScanningActivity

@Composable
fun HomeScreenWithScanner(
  modifier: Modifier = Modifier,
  vm: HomeScreenViewModel = viewModel(),
  onClickCheckin: () -> Unit = {},
  onCheckinScan: (QRUser) -> Unit = {}
) {
  val context = LocalContext.current
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
  ) {
    val result = it.data?.getStringExtra("SCAN_RESULT_KEY").toString()
    val qrUser = vm.addQRRecord(result)
    if (qrUser != null) {
      onCheckinScan(qrUser)
    } else {
      Toast.makeText(context, "QR is not valid", Toast.LENGTH_SHORT).show()
    }
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