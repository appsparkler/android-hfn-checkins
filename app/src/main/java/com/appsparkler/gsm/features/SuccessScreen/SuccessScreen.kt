package com.appsparkler.gsm.features.SuccessScreen

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes

fun NavHostController.navigateToSuccess() {
  navigate(
    AppRoutes.SUCCESS_SCREEN.name,
  ) {
    popUpTo(AppRoutes.HOME.name) {
      inclusive = true
    }
  }
}

fun NavGraphBuilder.successScreen(
  modifier: Modifier = Modifier,
  successScreenModel: SuccessScreenModel = SuccessScreenModel(),
  onClickReturnToMain: () -> Unit = {}
) {
  composable(route = AppRoutes.SUCCESS_SCREEN.name) {
    SuccessScreenView(
      modifier = modifier,
      dataModel = successScreenModel,
      onClickReturnToMain = onClickReturnToMain
    )
  }
}