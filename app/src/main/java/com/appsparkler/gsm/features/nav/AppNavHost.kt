package com.appsparkler.gsm.features.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.appsparkler.gsm.features.HomeScreen.HomeScreenViewModel
import com.appsparkler.gsm.features.HomeScreen.homeScreen
import com.appsparkler.gsm.features.HomeScreen.navigateToHome
import com.appsparkler.gsm.features.SuccessScreen.navigateToSuccess
import com.appsparkler.gsm.features.SuccessScreen.successScreen
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun AppNavHost(
  navController: NavHostController = rememberNavController(),
  homeScreenViewModel: HomeScreenViewModel = viewModel()
) {
  HFNTheme {
    NavHost(
      navController = navController,
      startDestination = AppRoutes.HOME.name
    ) {
      homeScreen(
        vm = homeScreenViewModel,
        onClickCheckin = { navController.navigateToSuccess() }
      )
      successScreen(
        onClickReturnToMain = { navController.navigateToHome() }
      )
    }
  }
}