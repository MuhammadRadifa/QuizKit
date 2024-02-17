package com.example.quizkit.ui.screen.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.quizkit.R
import com.example.quizkit.ui.component.CardQuiz

@Composable
fun CategoryListScreen(innerPadding:PaddingValues){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 16, topEndPercent = 16)
    ) {
        Column(modifier = Modifier
            .padding(20.dp)
            .padding(top = 30.dp)
            .fillMaxWidth()) {
            CardQuiz()
            CardQuiz()
            CardQuiz()
        }
    }

}