package com.example.quizkit.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizkit.data.common.Screen
import com.example.quizkit.ui.MainViewModel
import com.example.quizkit.ui.screen.authentication.LoginScreen
import com.example.quizkit.ui.screen.authentication.RegisterScreen
import com.example.quizkit.ui.screen.authentication.WelcomeScreen
import com.example.quizkit.ui.screen.onboarding.OnBoarding
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainApp(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val navController = rememberNavController()

    mainViewModel.token.collectAsState().value.let { token ->
        NavHost(
            navController = navController,
            startDestination = if (token == null) Screen.OnBoarding.route else Screen.Main.route
        ) {
            composable(Screen.OnBoarding.route) {
                OnBoarding(navController)
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController)
            }
            composable(Screen.Main.route) {
                MainScreen()
            }
        }
    }
}
