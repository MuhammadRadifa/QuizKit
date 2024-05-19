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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.quizkit.data.common.SettingsQuiz
import org.koin.androidx.compose.koinViewModel


@Composable
fun QuizOption(navigateToStart: (SettingsQuiz) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.option), contentDescription = "Option")
        Spacer(modifier = Modifier.height(16.dp))
        QuizOptionContent(navigateToStart)
    }
}

@Composable
fun QuizOptionContent(navigateToStart: (SettingsQuiz) -> Unit) {

    var amount by remember { mutableIntStateOf(10) }

    var type by remember { mutableStateOf("multiple") }

    var difficulty by remember { mutableStateOf("easy") }


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
            InputType(
                onClick = {
                    amount = it.toInt()
                },
                listType = listOf("10", "20", "30"),
                title = "Number of Questions",
                data = amount.toString(),
                painter = R.drawable.baseline_question_mark_24
            )
            InputType(
                onClick = {
                    difficulty = it
                },
                listType = listOf("easy", "medium", "hard","mixed"),
                title = "Difficulty of Questions",
                data = difficulty,
                painter = R.drawable.lv
            )
            InputType(
                onClick = {
                    type = it
                },
                listType = listOf("mixed", "multiple", "boolean"),
                title = "Type of Questions",
                data = type,
                painter = R.drawable.box_stype
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navigateToStart(
                        SettingsQuiz(
                            amount = amount,
                            type = type,
                            difficulty = difficulty,
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white_background),
                    containerColor = colorResource(id = R.color.primary_purple)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Save", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun InputType(
    onClick: (String) -> Unit,
    listType: List<String>,
    title: String,
    data:String,
    painter:Int
) {
    var isExpanded by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(16.dp))
    Column {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            Button(
                onClick = { isExpanded = true },
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
                                painter = painterResource(id = painter),
                                contentDescription = title,
                                tint = colorResource(id = R.color.primary_purple)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = data, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "Dropdown"
                    )
                }
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.fillMaxWidth(0.85F)
            ) {
                listType.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            onClick(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

