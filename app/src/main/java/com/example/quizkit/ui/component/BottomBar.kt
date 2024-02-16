package com.example.quizkit.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.quizkit.R
import com.example.quizkit.data.itemNavigation

@Composable
fun BottomBar(){
    NavigationBar(
        containerColor = colorResource(id = R.color.white)
    ) {
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