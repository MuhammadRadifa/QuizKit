package com.example.quizkit.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizkit.R
import com.example.quizkit.data.common.avatarProfile
import com.example.quizkit.data.common.backgroundProfile
import com.example.quizkit.ui.MainViewModel
import com.example.quizkit.ui.component.InputForm
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    profileViewModel: ProfileViewModel = koinViewModel()
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    mainViewModel.token.collectAsState().value.let { token ->
        profileViewModel.getUser(token!!).collectAsState(null).value.let {
            user ->
            if (user != null) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp),
                    color = colorResource(id = R.color.white_background),
                    shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
                ) {
                    Column(
                        Modifier
                            .padding(20.dp)
                            .padding(top = 80.dp)
                    ) {
                        InputForm(
                            title = "Username",
                            icon = R.drawable.user,
                            onChange = {},
                            value = user.name,
                            disable = true,
                            colorPaint = R.color.tertiary_blue
                        )
                        InputForm(
                            title = "Email",
                            icon = R.drawable.email,
                            onChange = {},
                            value = user.email,
                            disable = true,
                            colorPaint = R.color.tertiary_blue
                        )
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.size(180.dp),
                        color = colorResource(id = backgroundProfile[user.background]!!),
                        shape = RoundedCornerShape(20)
                    ) {
                        Image(
                            painter = painterResource(id = avatarProfile[user.avatar]!!),
                            contentDescription = "profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp)
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .size(48.dp)
                            .offset(x = 76.dp, y = 76.dp),
                        onClick = { showDialog = true },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colorResource(id = R.color.primary_purple),
                            contentColor = Color.White
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        )
                    }
                }

                if (showDialog) {
                    AlertDialog(onDismissRequest = { showDialog = false }) {
                        DialogAvatarProfile(
                            avatar = user.avatar,
                            background = user.background,
                            onClick = {
                                avatar, background ->
                                profileViewModel.updateAvatar(user.email, avatar, background)
                                showDialog = false
                            }
                        )
                    }
                }
            }
        }


    }


}