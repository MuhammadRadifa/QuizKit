package com.example.quizkit.ui.screen.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R


@Preview(showBackground = true)
@Composable
fun QuizResult() {
    Column(Modifier.background(color = colorResource(id = R.color.primary_purple))) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            )
        ) {
            Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.SpaceBetween) {
                BoxResult()
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth().padding(16.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_purple)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Close", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun BoxResult() {
    Column(Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.4f),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.primary_purple)
            ),
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(160.dp),
                    painter = painterResource(id = R.drawable.thropy),
                    contentDescription = "thropy"
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white_transparent)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Play Again", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.padding(8.dp)) {
            Row {
                TextResult(title = "CORRECT ANSWER", value = "7 QUESTION")
                Spacer(modifier = Modifier.weight(1f))
                TextResult(title = "COMPLETION", value = "70%")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextResult(title = "INCORRECT ANSWER", value = "3 QUESTION")
        }
    }
}

@Composable
fun TextResult(title: String, value: String) {
    Column {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.text_gray),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
