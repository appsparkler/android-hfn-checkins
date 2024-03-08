package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.appsparkler.gsm.features.nav.AppRoutes

fun NavHostController.navigateToHome() {
  navigate(AppRoutes.HOME.name)
}

fun NavGraphBuilder.homeScreen() {
  composable(route = AppRoutes.HOME.name) {
    HomeScreenView()
  }
}

