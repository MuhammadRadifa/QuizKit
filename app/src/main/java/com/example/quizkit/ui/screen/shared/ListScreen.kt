package com.example.quizkit.ui.screen.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.ui.component.CardQuiz

@Composable
fun ListScreen(innerPadding: PaddingValues){
    val listDummmy = listOf<String>()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding).padding(top = 20.dp),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if(listDummmy.isNotEmpty())Arrangement.Top else Arrangement.Center
        ){
            if(listDummmy.isNotEmpty()){
                LazyColumn {
                    items(listDummmy){
                        CardQuiz()
                    }
                }
            }else{
                Image(painter = painterResource(id = R.drawable.empty), contentDescription = "empty column")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "You Dont Have Any History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_gray)
                )
            }
        }

    }

}