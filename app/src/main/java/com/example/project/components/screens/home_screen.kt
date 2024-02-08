package com.example.project.components.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project.components.IconComponentDrawable
import com.example.project.components.SpaceHeight
import com.example.project.components.SpaceWidth
import com.example.project.components.data.Person
import com.example.project.components.data.personList
import com.example.project.components.navigation.CHAT_SCREEN
import com.example.project.ui.theme.Gray
import com.example.project.ui.theme.Gray400
import com.example.project.ui.theme.Line
import com.example.project.R
import com.example.project.components.data.Chat


@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFB851))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            HeaderView()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White, RoundedCornerShape(
                            topStart = 30.dp, topEnd = 30.dp
                        )
                    )
            ) {
                BottomSheetSwipeUp(
                    modifier = Modifier
                        .align(TopCenter)
                        .padding(top = 15.dp)
                )
                LazyColumn(
                    modifier = Modifier.padding(bottom = 15.dp, top = 30.dp)
                ) {
                    items(personList, key = { it.id }) {
                        UserEachRow(person = it, chatList = it.chatList, onClick = {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set("data", it)
                            navHostController.navigate(CHAT_SCREEN)
                        })
                    }
                }
            }
        }

    }

}

@Composable
fun HeaderView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp)
    ) {
        Header()

    }
}



@Composable
fun BottomSheetSwipeUp(
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .background(
                Gray400,
                RoundedCornerShape(90.dp)
            )
            .width(90.dp)
            .height(5.dp)

    )
}


@Composable
fun UserEachRow(
    person: Person,
    chatList: List<Chat>,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .noRippleEffect { onClick() }
            .padding(horizontal = 20.dp, vertical = 5.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    IconComponentDrawable(icon = person.icon, size = 60.dp)
                    SpaceWidth()
                    Column {
                        Text(
                            text = person.name, style = TextStyle(
                                color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                            )
                        )
                        SpaceHeight(5.dp)
                        Text(
                            text = person.status, style = TextStyle(
                                color = Gray, fontSize = 14.sp
                            )
                        )
                    }

                }
                Text(
                    text =  person.time, style = TextStyle(
                        color = Gray, fontSize = 12.sp
                    )
                )
            }
            SpaceHeight(15.dp)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Line)
        }
    }

}


@Composable
fun Header() {

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300
            )
        ) {
            append(stringResource(R.string.welcome))
        }
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        ) {
            append(stringResource(R.string.user))
        }
    }

    Text(text = annotatedString)

}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.noRippleEffect(onClick: () -> Unit) = composed {
    clickable(
        interactionSource = MutableInteractionSource(),
        indication = null
    ) {
        onClick()
    }
}