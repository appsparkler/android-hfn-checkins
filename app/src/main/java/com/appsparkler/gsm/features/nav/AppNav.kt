package com.appsparkler.gsm.features.nav

import androidx.navigation.NavHostController
import com.appsparkler.gsm.features.SuccessScreen.navigateToSuccess

class AppNav(val navHostController: NavHostController) {

  fun gotoSuccessScreen() {
    navHostController.navigateToSuccess()
  }
}
