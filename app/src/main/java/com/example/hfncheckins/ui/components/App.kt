package com.example.hfncheckins.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen.AbhyasiIdCheckinScreen
import com.example.hfncheckins.ui.components.CheckinSuccessScreen.CheckinSuccessScreen
import com.example.hfncheckins.ui.components.MainScreen.MainScreen
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onClickScan: (NavHostController) -> Unit
) {
    HFNTheme {
        Scaffold(
            modifier = Modifier.padding(8.dp)
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Routes.MAIN_SCREEN.name
            ) {
                navController.enableOnBackPressed(enabled = false)
                composable(Routes.MAIN_SCREEN.name) {
                    MainScreen(
                        event = getSampleEvent(),
                        onStartCheckin = { inputValue, type ->

                        },
                        onClickScan = {
                            onClickScan(navController)
                        }
                    )
                }
                composable(
                    route = "${Routes.ABHYASI_CHECKIN_DETAIL_SCREEN.name}?code={code}",
                    arguments = listOf(
                        navArgument("code") {
                            type = NavType.StringType
                            defaultValue = "Default"
                        }
                    )
                ) {
                    it.arguments?.getString("code")?.let {
                        if (it.isNotEmpty()) {
                            var abhyasiIdCheckin by remember {
                                mutableStateOf(
                                    AbhyasiIdCheckin(
                                        abhyasiId = it,
                                        dormAndBerthAllocation = "",
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            }
                            AbhyasiIdCheckinScreen(
                                abhyasiIdCheckin = abhyasiIdCheckin,
                                onClickCheckin = {
                                    navController.navigate(
                                        Routes.CHECKIN_SUCCESS_SCREEN.name
                                    )
                                    abhyasiIdCheckin = abhyasiIdCheckin.copy(
                                        abhyasiId = "",
                                        dormAndBerthAllocation = "",
                                    )
                                },
                                onClickCancel = {
                                    navController.navigate(
                                        Routes.MAIN_SCREEN.name
                                    )
                                    navController.enableOnBackPressed(enabled = false)
                                },
                                onChangeDormAndBerthAllocation = {
                                    abhyasiIdCheckin = abhyasiIdCheckin.copy(
                                        dormAndBerthAllocation = it
                                    )
                                }
                            )
                        } else {
                            Text("Something else")
                        }
                    }
                }
                composable(
                    route = Routes.CHECKIN_SUCCESS_SCREEN.name
                ) {
                    CheckinSuccessScreen(
                        onClickReturnToMain = {
                            navController.navigate(Routes.MAIN_SCREEN.name)
                        }
                    )
                }
            }
        }
    }
}