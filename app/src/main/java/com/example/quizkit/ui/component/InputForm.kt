package com.example.quizkit.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R

@Composable
fun InputForm(
    title:String,icon:Int,
    disable:Boolean = false,
    colorPaint:Int = R.color.white,
    value:String= "",
    onChange:(String)->Unit){
    val textInput = ""


    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value.ifBlank { textInput },
        onValueChange = {},
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = colorResource(id = R.color.primary_purple)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = colorPaint),
            unfocusedIndicatorColor = colorResource(id = colorPaint)
        ),
        //label = { Text(text = "Your $title") }
    )
}