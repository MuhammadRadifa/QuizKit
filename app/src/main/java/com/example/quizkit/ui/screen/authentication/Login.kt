package com.example.quizkit.ui.screen.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import com.example.quizkit.ui.component.InputForm
import com.example.quizkit.ui.component.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = koinViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(navBackStackEntry = navBackStackEntry, navigateBack = {
                navController.popBackStack()
            })
        },
        containerColor = colorResource(id = R.color.tertiary_blue),
        contentColor = Color.Black
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            InputForm(title = "Email Address", value = email, icon = R.drawable.email, onChange = {
                email = it
            })
            InputForm(title = "Password", value = password, icon = R.drawable.lock, onChange = {
                password = it
            })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_purple)
                )
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                        )
                    ) {
                        append("Don't Have Account? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.primary_purple)
                        )
                    ) {
                        append("Register here")
                    }

                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().clickable {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        val alert = authViewModel.loginThread.value

        if(alert.loading){
            //show loading
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }else{
            alert.data?.let {
                //show success
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                authViewModel.saveToken(email)
                navController.navigate(Screen.Main.route)
            }
            alert.error?.let {
                //show error
                error ->
                Log.e("error", error)
            }
        }
    }
}
