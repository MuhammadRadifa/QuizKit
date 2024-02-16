package com.example.quizkit.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.ui.component.CardQuiz

@Composable
fun HomeScreen(innerPadding:PaddingValues = PaddingValues(20.dp)){
    Column(
        Modifier
            .padding(innerPadding)
            ) {
        Column(Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            RecentBoard()
            Spacer(modifier = Modifier.height(16.dp))
            PlayBoard()
        }
        PopularList()
    }
}

@Composable
fun RecentBoard(){
    val recentList = listOf<String>("a")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE4EAFF),
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = if(recentList.isNotEmpty())"Recent Quiz" else "Lets Play Quiz",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                if(recentList.isNotEmpty()){
                    Text(
                        text = "History - Education",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.text_purple)
                    )
                }

            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight, 
                contentDescription = "recent-play",
                modifier = Modifier.size(30.dp),
                tint = colorResource(id = R.color.text_purple)
                )
        }
    }
}

@Composable
fun PlayBoard(){
    Surface(color = Color(0x4DF1F4FF), shape = RoundedCornerShape(12)) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Embark on a journey of collective discovery, where challenges uplift knowledge universally",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorResource(id = R.color.text_purple)
                )
            ) {
                Text(text = "Let's Play", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PopularList(){
    Surface(
        Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 16, topEndPercent = 16)
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .padding(top = 16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "Popular Quiz",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                Text(
                    text = "See All",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.text_purple)
                )
            }
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                CardQuiz()
                CardQuiz()
            }
        }
    }
}