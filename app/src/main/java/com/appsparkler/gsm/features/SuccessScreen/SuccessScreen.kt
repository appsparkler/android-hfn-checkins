package com.appsparkler.gsm.features.SuccessScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes

fun NavHostController.navigateToSuccess() {
  navigate(AppRoutes.SUCCESS_SCREEN.name)
}

fun NavGraphBuilder.successScreen() {
  composable(route = AppRoutes.SUCCESS_SCREEN.name) {
    SuccessScreenView()
  }
}