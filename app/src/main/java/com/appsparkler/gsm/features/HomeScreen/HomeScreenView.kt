package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsparkler.gsm.features.ScanButton.ScanButton

@Composable
fun HomeScreenView(
  modifier: Modifier = Modifier,
  vm: HomeScreenViewModel = viewModel(),
  onClickCheckin: () -> Unit = {}
) {
  Scaffold(
    modifier = modifier,
    floatingActionButton = {
      ScanButton(
        onClick = vm::onClickScan
      )
    }
  ) {
    LazyColumn(
      modifier = Modifier
        .padding(it)
        .padding(12.dp)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      item {
        HomeScreenContentWithViewModel(
          vm = vm,
          onCheckin = onClickCheckin
        )
      }
    }
  }
}

@Preview
@Composable
fun HomeScreenViewPreview() {
  HomeScreenView()
}