package com.example.quizkit.presentation

import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.quizkit.R
import com.example.quizkit.ui.component.BottomBar
import com.example.quizkit.ui.component.FloatingButton

@Composable
fun MainScreen(){
    Scaffold(
        floatingActionButton = { FloatingButton()},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar()},
        containerColor = colorResource(id = R.color.primary_purple)
    ) {
        it
    }
}
