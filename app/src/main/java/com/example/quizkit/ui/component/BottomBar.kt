package com.example.quizkit.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizkit.R
import com.example.quizkit.data.itemNavigation

@Composable
fun BottomBar(){
    NavigationBar(containerColor = Color.White) {
        itemNavigation.forEach{
            items ->
            NavigationBarItem(
                modifier = Modifier.padding(vertical = 20.dp),
                selected = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.primary_purple),
                    unselectedIconColor = colorResource(id = R.color.secondary_blue),
                    indicatorColor = Color.White
                ),
                onClick = { /*TODO*/ },
                icon = {
                    if(items.title != "WhiteSpace"){
                        Icon(
                            painter = painterResource(id = items.icon),
                            contentDescription = items.title,
                            modifier = Modifier.size(32.dp),
                        )
                    }
                }
            )
        }
    }
}