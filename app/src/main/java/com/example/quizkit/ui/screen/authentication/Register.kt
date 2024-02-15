package com.example.quizkit.ui.screen.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.ui.component.InputForm
import com.example.quizkit.ui.component.TopBar

@Composable
fun RegisterScreen(){
    Scaffold(
        topBar = { TopBar("Login") },
        containerColor = colorResource(id = R.color.tertiary_blue)
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 30.dp)) {
            InputForm(title = "Username", icon = R.drawable.user, onChange = {})
            InputForm(title = "Email Address", icon = R.drawable.email,onChange = {})
            InputForm(title = "Password", icon = R.drawable.lock,onChange = {})
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_purple)
                )
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                    )
                    ){
                        append("Already Have an Account? ")
                    }
                    withStyle(style = SpanStyle(
                        color = colorResource(id = R.color.primary_purple)
                    )
                    ){
                        append("Login here")
                    }

                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}