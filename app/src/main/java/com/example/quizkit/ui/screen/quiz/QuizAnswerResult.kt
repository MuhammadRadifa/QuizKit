package com.example.quizkit.ui.screen.quiz

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.quizkit.R
import com.example.quizkit.data.common.iconCategory
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.data.network.remote.response.ResultsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizAnswerResultScreen(
    id: Int,
    quizViewModel: QuizViewModel = koinViewModel()
) {
    Log.i("QuizAnswerResultScreen", "id: $id")
    quizViewModel.getHistoryById(id)
    Column(Modifier.background(color = colorResource(id = R.color.primary_purple))) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            )
        ) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                quizViewModel.history.collectAsState().value.let {
                    when {
                        it.loading -> {
                            Text(text = "Loading")
                        }

                        it.data != null -> {
                            QuizAnswerResultContent(
                                it.data
                            )
                        }

                        it.error != null -> {
                            Text(text = it.error)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizAnswerResultContent(
    data: HistoryEntity
) {
    val gson = Gson()
    val listType = object : TypeToken<List<ResultsItem>>() {}.type
    val resultsItem: List<ResultsItem> = gson.fromJson(data.question, listType)

    val listTypeString = object : TypeToken<List<String>>() {}.type
    val correctAnswerData: List<String> = gson.fromJson(data.correctAnswerData, listTypeString)
    val userAnswerData: List<String> = gson.fromJson(data.userAnswerData, listTypeString)


    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = colorResource(id = R.color.primary_blue),
                shape = RoundedCornerShape(10.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = iconCategory[data.category] ?: R.drawable.box_stype
                    ),
                    contentDescription = "logo",
                    modifier = Modifier.padding(10.dp),
                    tint = colorResource(id = R.color.primary_purple)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = data.quiz,
                maxLines = 1,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))


        //loop data
        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(resultsItem) { index, item ->
                val incorrectAnswers = item.incorrectAnswers
                val combinedAnswers =
                    mutableListOf<String>() // Use mutable list to allow adding elements
                combinedAnswers.addAll(incorrectAnswers) // Add all incorrect answers at once

                val correctAnswer = item.correctAnswer

                // Generate a random index within the bounds of combinedAnswers + 1 for the correct answer
                val randomIndex = (0..combinedAnswers.size).random()
                combinedAnswers.add(randomIndex, correctAnswer)
                Column {
                    Text(
                        text = "QUESTION ${index + 1} OF ${resultsItem.size}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.text_gray)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            append(
                                HtmlCompat.fromHtml(
                                    resultsItem[index].question,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                ).toString()
                            )
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    //lazy column
//                    val isSelected = remember { mutableStateOf(false) }
                    Column(
                        Modifier
                            .fillMaxHeight(0.8f)
                    ) {

                        combinedAnswers.forEach {

                            Button(
                                onClick = {

                                },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor =
                                    colorResource(
                                        id = if (it == correctAnswerData[index] && it == userAnswerData[index]) R.color.green else if (it == correctAnswerData[index] && it != userAnswerData[index]) R.color.green else if (it != correctAnswerData[index] && it == userAnswerData[index]) R.color.red else R.color.white
                                    ),
                                    contentColor = colorResource(id = if(it == correctAnswerData[index] || it == userAnswerData[index]) R.color.white else R.color.black)
                                ),
                                border = BorderStroke(0.5.dp, colorResource(id = R.color.black)),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                //if long is 16 sp if short is 20 sp
                                Text(
                                    text = buildAnnotatedString {
                                        append(
                                            HtmlCompat.fromHtml(
                                                it,
                                                HtmlCompat.FROM_HTML_MODE_LEGACY
                                            ).toString()
                                        )
                                    },
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }


            }
        }
    }
}