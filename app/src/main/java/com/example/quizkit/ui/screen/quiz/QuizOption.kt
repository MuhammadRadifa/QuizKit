package com.example.quizkit.ui.screen.quiz

import android.text.InputType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R

@Preview
@Composable
fun QuizOption() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.option), contentDescription = "Option")
        Spacer(modifier = Modifier.height(16.dp))
        QuizOptionContent()
    }
}

@Composable
fun QuizOptionContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_background),
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Quiz Option",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            InputType()
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white_background),
                    containerColor = colorResource(id = R.color.primary_purple)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Let's Play", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun InputType() {
    Spacer(modifier = Modifier.height(16.dp))
    Column {
        Text(text = "Number of Question", fontSize = 18.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.tertiary_blue),
                    contentColor = colorResource(id = R.color.black)
                )
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            Modifier
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(color = colorResource(id = R.color.white))
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp),
                                painter = painterResource(id = R.drawable.baseline_question_mark_24),
                                contentDescription = "10 Questions",
                                tint = colorResource(id = R.color.primary_purple)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "10 Question", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "Dropdown"
                    )
                }
            }
            DropdownMenu(
                expanded = true,
                onDismissRequest = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.85F)
            ) {
                DropdownMenuItem(text = { Text(text = "Ad") }, onClick = { /*TODO*/ })
            }
        }
    }
}

