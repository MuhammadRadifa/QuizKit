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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import com.example.quizkit.data.common.itemNavigation

@Composable
fun BottomBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry?
){
    NavigationBar(
        containerColor = colorResource(id = R.color.white)
    ) {
        itemNavigation.forEach{
            items ->
            val isSelected =
                items.title == (navBackStackEntry?.destination?.route ?: Screen.Home.route)
            NavigationBarItem(
                modifier = Modifier.padding(vertical = 20.dp),
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.primary_purple),
                    unselectedIconColor = colorResource(id = R.color.secondary_blue),
                    indicatorColor = Color.White
                ),
                onClick = { navController.navigate(items.title) },
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