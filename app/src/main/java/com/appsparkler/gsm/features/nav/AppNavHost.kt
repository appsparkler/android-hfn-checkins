package com.appsparkler.gsm.features.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.appsparkler.gsm.features.HomeScreen.HomeScreenViewModel
import com.appsparkler.gsm.features.HomeScreen.homeScreen
import com.appsparkler.gsm.features.HomeScreen.navigateToHome
import com.appsparkler.gsm.features.SuccessScreen.SuccessScreenModel
import com.appsparkler.gsm.features.SuccessScreen.navigateToSuccess
import com.appsparkler.gsm.features.SuccessScreen.successScreen
import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.hfncheckins.ui.hfnTheme.HFNTheme

@Composable
fun App() {
  val successScreenModel = SuccessScreenModel()
  val homeScreenViewModel: HomeScreenViewModel = viewModel()
  val navController = rememberNavController()
  HFNTheme {
    AppNavHost(
      homeScreenViewModel = homeScreenViewModel,
      successScreenModel = successScreenModel,
      navController = navController
    )
  }
}

@Composable
fun AppNavHost(
  navController: NavHostController = rememberNavController(),
  homeScreenViewModel: HomeScreenViewModel = viewModel(),
  successScreenModel: SuccessScreenModel = SuccessScreenModel()
) {
  val homeScreenViewModelState = homeScreenViewModel.state
  NavHost(
    navController = navController,
    startDestination = AppRoutes.HOME.name
  ) {
    homeScreen(
      vm = homeScreenViewModel,
      onClickCheckin = {
        successScreenModel.setAManualEntryUser(
          ManualEntryUser(
            name = homeScreenViewModelState.value.name,
            mobileNo = homeScreenViewModelState.value.mobileNo,
            email = homeScreenViewModelState.value.email,
            organization = homeScreenViewModelState.value.organization
          )
        )
        navController.navigateToSuccess()
      }
    )
    successScreen(
      onClickReturnToMain = { navController.navigateToHome() },
      successScreenModel = successScreenModel

    )
  }
}


//fun handleClickCheckin(
//  navController: NavHostController,
//  homeScreenViewModelState: StateFlow<HomeScreenState>,
//  successScreenModel: SuccessScreenModel
//): () -> Unit {
//  successScreenModel.setAManualEntryUser(
//    ManualEntryUser(
//      name = homeScreenViewModelState.value.name,
//      mobileNo = homeScreenViewModelState.value.mobileNo,
//      email = homeScreenViewModelState.value.email,
//      organization = homeScreenViewModelState.value.organization
//    )
//  )
//  navController.navigateToSuccess()
//  return {}
//}
