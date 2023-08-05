package com.example.hfncheckins.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.ui.components.MainScreen.MainScreen

@Composable fun App(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onClickScan: (NavHostController) -> Unit
) {
    HFNTheme {
        Scaffold {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Routes.MAIN_SCREEN.name
            ) {
                composable(Routes.MAIN_SCREEN.name) {
                    MainScreen(
                        event = getSampleEvent(),
                        onStartCheckin = {},
                        onClickScan = {
                            onClickScan(navController)
                        }
                    )
                }
                composable(
                    route = "TestRoute?code={code}",
                    arguments = listOf(
                        navArgument("code") {
                            type = NavType.StringType
                            defaultValue = "Default"
                        }
                    )
                ) {
                    it.arguments?.getString("code")?.let {
                        if(it.contains("abc")) {
                            Text(text = "Hello World")
                        } else {
                            Text("Something else")
                        }
                    }
                }
            }
        }
    }
}