package com.example.quizkit.ui.component


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import com.example.quizkit.data.common.avatarProfile
import com.example.quizkit.data.common.backgroundProfile
import com.example.quizkit.data.common.titleTopBar
import com.example.quizkit.ui.MainViewModel
import com.example.quizkit.ui.screen.home.HomeViewModel
import com.example.quizkit.ui.utils.DateConverter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun TopBar(navBackStackEntry: NavBackStackEntry?, navigateBack: () -> Unit) {
    val title = navBackStackEntry?.destination?.route ?: Screen.Home.route
    when (title) {
        Screen.Home.route -> TopBarProfile()
        else -> TopBarLeftIcon(title, navigateBack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLeftIcon(title: String, navigateBack: () -> Unit) {

    val colorPaint: Color =
        if (title == "Login" || title == "Register") Color.Black else Color.White

    CenterAlignedTopAppBar(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = titleTopBar[title] ?: title,
                fontWeight = FontWeight.Bold,
                color = colorPaint,
                fontSize = 28.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = colorPaint,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(homeViewModel: HomeViewModel = koinViewModel(),mainViewModel: MainViewModel = koinViewModel()) {
    mainViewModel.token.collectAsState().value.let {
        token ->
        if (token != null) {
            homeViewModel.getUser(token).collectAsState(initial = null).value.let { user ->
                if (user != null) {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(top = 16.dp),
                        title = {
                            Column {
                                Text(
                                    text = DateConverter(System.currentTimeMillis()),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFFFFD6DD)
                                )
                                Text(
                                    text = user.name,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }
                        },
                        actions = {
                            Surface(
                                color = colorResource(id = backgroundProfile[user.background]!!),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(60.dp)
                                    .padding(horizontal = 20.dp),
                                shape = RoundedCornerShape(20)
                            ) {
                                Image(
                                    painter = painterResource(id = avatarProfile[user.avatar]!!),
                                    contentDescription = "profile",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(top = 10.dp)
                                )
                            }
                        }
                    )
                } else {
                    Text(text = "Loading...")
                }
            }
        }
    }
}

