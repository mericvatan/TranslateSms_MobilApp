package com.example.project.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project.components.ButtonComponent
import com.example.project.components.SpaceWidth
import com.example.project.components.navigation.HOME_SCREEN
import com.example.project.R
@Composable
fun StartScreen(
    navHostController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), contentDescription = "",
            contentScale = ContentScale.FillWidth
        )

        Box(
            modifier = Modifier
                .padding(top = 220.dp)
                .background(Color.Black)
                .align(Alignment.Center)

        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_name),
                    style = TextStyle(
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                CustomCheckBox()
            }
        }
        ButtonComponent(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter)
                .height(60.dp)
        ) {
            navHostController.navigate(HOME_SCREEN)
        }
    }
}


@Composable
fun CustomCheckBox() {

    Row(
        modifier = Modifier.padding(vertical = 15.dp)
    ) { {

        }
        Text(
            text = stringResource(R.string.talk_with_your_friends), style = TextStyle(
                color = Color(0xFFFFB851),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }

}