package com.example.quizkit.presentation

import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import com.example.quizkit.ui.component.BottomBar
import com.example.quizkit.ui.component.FloatingButton
import com.example.quizkit.ui.component.TopBar
import com.example.quizkit.ui.screen.category.CategoryScreen
import com.example.quizkit.ui.screen.home.HomeScreen
import com.example.quizkit.ui.screen.profile.ProfileScreen
import com.example.quizkit.ui.screen.shared.ListScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = { TopBar(navBackStackEntry = navBackStackEntry)},
        floatingActionButton = { FloatingButton()},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController,navBackStackEntry = navBackStackEntry) },
        containerColor = colorResource(id = R.color.primary_purple)
    ) {
        innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Home.route){
            composable(Screen.Home.route){
                HomeScreen(innerPadding = innerPadding)
            }
            composable(Screen.Category.route){
                CategoryScreen(innerPadding = innerPadding)
            }
            composable(Screen.Profile.route){
                ProfileScreen(innerPadding = innerPadding)
            }
            composable(Screen.History.route){
                ListScreen(innerPadding = innerPadding)
            }
        }

    }
}
