package com.example.quizkit.ui.screen.quiz

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.quizkit.R
import com.example.quizkit.data.common.SettingsQuiz
import com.example.quizkit.data.common.iconCategory
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.data.network.remote.response.ResultsItem
import com.example.quizkit.ui.utils.countMatchingElements
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizMode(
    quizName: String,
    category: String,
    settingsQuiz: SettingsQuiz,
    navigateToResult: (Int, Int) -> Unit,
    navigateToStart: () -> Unit,
    quizViewModel: QuizViewModel = koinViewModel()
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple))
    ) {
        LaunchedEffect(key1 = settingsQuiz) {
            quizViewModel.getQuiz(settingsQuiz)
        }
        quizViewModel.quiz.collectAsState().value.let { quiz ->
            when {
                quiz.loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.undraw_happy_music_g6wc),
                            contentDescription = "loading"
                        )
                    }
                }

                quiz.error != null -> {
                    Toast.makeText(LocalContext.current, quiz.error, Toast.LENGTH_SHORT).show()
                }

                quiz.data != null -> {
                    val pagerState = rememberPagerState {
                        quiz.data.size
                    }
                    val dialogState = remember { mutableStateOf(false) }
                    val answer = remember { mutableListOf<String>() }
                    QuizProgress(quiz.data.size, pagerState) {
                        dialogState.value = dialogState.value.not()
                    }
                    QuizQuestion(
                        quizViewModel,
                        quiz.data,
                        pagerState,
                        answer,
                        quizName,
                        category,
                        navigateToResult
                    )
                    if (dialogState.value) {
                        AlertDialog(
                            containerColor = colorResource(id = R.color.white),
                            title = {
                                Text(
                                    text = "Are you sure you want to exit?"
                                )
                            },
                            text = {
                                Text(
                                    text = "Your progress will not be saved and the quiz will not be recorded"
                                )
                            },
                            onDismissRequest = {
                                dialogState.value = false
                            },
                            confirmButton = {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(
                                        onClick = {
                                            dialogState.value = false
                                        }
                                    ) {
                                        Text(text = "Cancel")
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Button(
                                        onClick = {
                                            navigateToStart()
                                        }
                                    ) {
                                        Text(text = "Yes, exit")
                                    }
                                }

                            }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizProgress(total: Int, pagerState: PagerState, onClick: () -> Unit) {
    Row(
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.orange_primary))
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
                text = "${pagerState.currentPage + 1} / ${total}",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Black
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = (pagerState.currentPage + 1).toFloat() / total.toFloat(),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(5.dp),
            color = colorResource(id = R.color.white),
            trackColor = colorResource(id = R.color.white_transparent)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white_transparent),

                ),

            contentPadding = PaddingValues(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(2.dp, colorResource(id = R.color.white))
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizQuestion(
    quizViewModel: QuizViewModel,
    quiz: List<ResultsItem>,
    pagerState: PagerState,
    answer: MutableList<String>,
    quizName: String,
    category: String,
    navigateToResult: (Int, Int) -> Unit
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val gson = Gson()

    val incorrectAnswers = quiz[pagerState.currentPage].incorrectAnswers
    val combinedAnswers = mutableListOf<String>() // Explicitly define type as List<String>
    combinedAnswers.addAll(incorrectAnswers) // Add all incorrect answers at once
    val correctAnswer = quiz.map { it.correctAnswer }


    val randomIndex = (0 until combinedAnswers.size).random()
    combinedAnswers.add(randomIndex, quiz[pagerState.currentPage].correctAnswer)

    Card(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_background)
        )
    ) {

        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = colorResource(id = R.color.primary_blue),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        painter = painterResource(
                            id = iconCategory[category] ?: R.drawable.box_stype
                        ),
                        contentDescription = "logo",
                        modifier = Modifier.padding(10.dp),
                        tint = colorResource(id = R.color.primary_purple)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = quizName,
                    maxLines = 1,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
            ) {
                Column {
                    Text(
                        text = "QUESTION ${pagerState.currentPage + 1} OF ${quiz.size}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.text_gray)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            append(
                                HtmlCompat.fromHtml(
                                    quiz[pagerState.currentPage].question,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                ).toString()
                            )
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    //lazy column
                    val isSelected = remember { mutableStateOf(false) }
                    Column(
                        Modifier
                            .fillMaxHeight(0.8f)
                            .verticalScroll(rememberScrollState())
                    ) {

                        combinedAnswers.forEach {
                            isSelected.value =
                                answer.size > pagerState.currentPage && answer[pagerState.currentPage] == it
                            Button(
                                onClick = {
                                    answer.add(pagerState.currentPage, it)
                                    if (answer.size > pagerState.currentPage + 1) {
                                        answer.removeAt(pagerState.currentPage + 1)
                                    }

                                    isSelected.value =
                                        answer.size > pagerState.currentPage && answer[pagerState.currentPage] == it
                                },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = if (isSelected.value) R.color.primary_purple else R.color.white),
                                    contentColor = colorResource(id = if (isSelected.value) R.color.white else R.color.black)
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
                }
            }
            val isSubmit = pagerState.pageCount == pagerState.currentPage + 1
            Row {
                if (pagerState.currentPage != 0) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        modifier = Modifier
                            .border(
                                1.5.dp,
                                colorResource(id = R.color.primary_purple),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .height(64.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back",
                            tint = colorResource(id = R.color.primary_purple)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (answer.size != pagerState.currentPage + 1) {
                            Log.i("answer", answer.size.toString())
                            Log.i("pageCount", pagerState.currentPage.toString())
                            Toast.makeText(
                                context,
                                "Please select an answer",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (!isSubmit) {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        } else {

                            val countMatch = countMatchingElements(correctAnswer, answer)
                            val answerSize = answer.size

                            val data = HistoryEntity(
                                id = 0,
                                quiz = quizName,
                                category = category,
                                correctAnswer = countMatch,
                                size = answerSize,
                                question = gson.toJson(quiz),
                                correctAnswerData = gson.toJson(correctAnswer),
                                userAnswerData = gson.toJson(answer)
                            )

                            quizViewModel.insertHistory(data)

                            navigateToResult(countMatch, answerSize)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_purple),
                    ),
                ) {
                    Text(text = if (isSubmit) "Submit" else "next", fontSize = 20.sp)
                }
            }
        }
    }
}