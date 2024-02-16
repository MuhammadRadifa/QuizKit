package com.example.quizkit.presentation

import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.quizkit.R
import com.example.quizkit.ui.component.BottomBar
import com.example.quizkit.ui.component.FloatingButton
import com.example.quizkit.ui.component.TopBar
import com.example.quizkit.ui.screen.home.HomeScreen

@Composable
fun MainScreen(){
    Scaffold(
        topBar = { TopBar(title = "home")},
        floatingActionButton = { FloatingButton()},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar()},
        containerColor = colorResource(id = R.color.primary_purple)
    ) {
        HomeScreen(it)
    }
}
