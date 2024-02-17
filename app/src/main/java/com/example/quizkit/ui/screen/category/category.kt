package com.example.quizkit.ui.screen.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.data.Category
import com.example.quizkit.data.itemCategory

@Composable
fun CategoryScreen(innerPadding:PaddingValues){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding).padding(top = 20.dp),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
    ) {
        LazyVerticalGrid(modifier = Modifier
            .padding(20.dp)
            .padding(top = 10.dp)
            .fillMaxWidth(),columns = GridCells.Fixed(2)){
            items(itemCategory){
                category ->
                CategoryCardItem(items = category)
            }
        }
    }
}

@Composable
fun CategoryCardItem(items:Category){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.tertiary_purple),
            contentColor = colorResource(id = R.color.text_purple)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Surface(
                modifier = Modifier.size(64.dp),
                color = colorResource(id = R.color.white_background),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = items.icon), contentDescription = items.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = items.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${items.amountQuiz} Quiz",
                fontWeight = FontWeight(400),
                fontSize = 16.sp
            )
        }
    }
}