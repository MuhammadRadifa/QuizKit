package com.example.quizkit.ui.screen.history

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.data.common.iconCategory
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.ui.component.CardQuiz
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    navigateToHistory: (Int, Int, Int, String, String) -> Unit,
    historyViewModel: HistoryViewModel = koinViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
    ) {
        historyViewModel.getHistory().collectAsState(initial = emptyList()).value.let { history ->
            Log.i("HistoryScreen", "History: $history")
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = if (history.isNotEmpty()) Arrangement.Top else Arrangement.Center
            ) {
                if (history.isNotEmpty()) {
                    LazyColumn {
                        items(history) { dataHistory ->
                            HistoryCard(
                                history = dataHistory,
                                navigateToHistory = navigateToHistory
                            )
                        }
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.empty),
                        contentDescription = "empty column"
                    )
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(history: HistoryEntity, navigateToHistory: (Int,Int, Int, String, String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, color = colorResource(id = R.color.secondary_blue)),
        onClick = {
            navigateToHistory(
                history.id,
                history.correctAnswer,
                history.size,
                history.quiz,
                history.category
            )
        },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Surface(
                    color = colorResource(id = R.color.primary_blue),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        painter = painterResource(
                            id = iconCategory[history.category] ?: R.drawable.box_stype
                        ),
                        contentDescription = "logo",
                        modifier = Modifier.padding(10.dp),
                        tint = colorResource(id = R.color.primary_purple)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = history.quiz,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = history.category,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Quiz-play",
                modifier = Modifier.size(30.dp),
                tint = colorResource(id = R.color.text_purple)
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}