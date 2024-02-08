package com.example.project.components.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project.R
import com.example.project.components.IconComponentDrawable
import com.example.project.components.IconComponentImageVector
import com.example.project.components.SpaceWidth
import com.example.project.components.data.Person
import com.example.project.components.data.Chat
import com.example.project.ui.theme.Gray
import com.example.project.ui.theme.Gray400
import com.example.project.ui.theme.LightRed
import com.example.project.ui.theme.LightYellow
import com.example.project.ui.theme.Yellow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

@Composable
fun ChatScreen(
    navHostController: NavHostController
) {

    var message by remember { mutableStateOf("") }
    var person by remember { mutableStateOf(navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data") ?: Person()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFB851))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            UserNameRow(
                person = person,
                modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White, RoundedCornerShape(
                            topStart = 30.dp, topEnd = 30.dp
                        )
                    )
                    .padding(top = 25.dp)

            ) {
                LazyColumn(
                    modifier = Modifier.padding(
                        start = 15.dp,
                        top = 25.dp,
                        end = 15.dp,
                        bottom = 75.dp
                    )
                ) {
                    items(person.chatList) { chat ->
                        ChatRowWithButton(chat = chat) {

                            translateAndShowMessage(chat.message) { translatedText ->

                            }
                        }
                    }
                }
            }
        }

        CustomTextField(
            text = message, onValueChange = { message = it },
            onSendClick = {
                // Gönderme işlemleri
                if (message.isNotEmpty()) {

                    val newChat = Chat(
                        id = person.chatList.size + 1,
                        message = message,
                        direction = false,
                        time = "Now"
                    )
                    person = person.copy(chatList = person.chatList + listOf(newChat))
                    message = ""
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(BottomCenter)
        )
    }
}

@Composable
fun ChatRowWithButton(
    chat: Chat,
    onButtonClick: () -> Unit
) {
    var translatedMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.direction) Alignment.Start else Alignment.End
    ) {
        // ChatRow
        Box(
            modifier = Modifier
                .background(
                    if (chat.direction) LightRed else LightYellow,
                    RoundedCornerShape(100.dp)
                ),
            contentAlignment = Center
        ) {
            Text(
                text = chat.message,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                textAlign = if (chat.direction) TextAlign.Start else TextAlign.End
            )
        }

        if (chat.direction) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(if (chat.direction) Alignment.Start else Alignment.End)
                    .clickable {
                        // İkona tıklandığında burada çeviriyi tetikleyin
                        translateAndShowMessage(chat.message) { translatedText ->
                            translatedMessage = translatedText
                        }
                        onButtonClick()
                    },
                contentAlignment = Alignment.TopEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val vectorImage = painterResource(id = R.drawable.ic_tr)
                    Icon(
                        painter = vectorImage,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )

                    Text("Çevir")
                }
            }
        }
        Text(
            text = chat.time,
            style = TextStyle(
                color = Gray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
        )


        translatedMessage?.let { message ->
            Text(
                text = "Çeviri: $message",
                modifier = Modifier
                    .padding(8.dp)
                    .align(if (chat.direction) Alignment.Start else Alignment.End)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text, onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = stringResource(R.string.message),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray400,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = { CommonIconButton(imageVector = Icons.Default.Add) },
        trailingIcon = {
            ClickableIcon(
                icon = R.drawable.ic_send,
                onClick = { onSendClick() }
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape
    )

}

@Composable
fun ClickableIcon(
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}




@Composable
fun UserNameRow(
    modifier: Modifier = Modifier,
    person: Person
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {

            IconComponentDrawable(icon = person.icon, size = 42.dp)
            SpaceWidth()
            Column {
                Text(
                    text = person.name, style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = stringResource(R.string.online), style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }
        }
        IconComponentImageVector(icon = Icons.Default.MoreVert, size = 24.dp, tint = Color.White)
    }

}

private fun translateAndShowMessage(sourceText: String, onTranslationComplete: (String) -> Unit) {
    val sourceLanguageCode = "tr"
    val targetLanguageCode = "en"

    val translatorOption = TranslatorOptions.Builder()
        .setSourceLanguage(sourceLanguageCode)
        .setTargetLanguage(targetLanguageCode)
        .build()
    val translator = Translation.getClient(translatorOption)

    val downloadConditions = DownloadConditions.Builder()
        .requireWifi()
        .build()

    translator.downloadModelIfNeeded(downloadConditions)
        .addOnSuccessListener {

            startTranslation(translator, sourceText, onTranslationComplete)
        }
        .addOnFailureListener { e ->

            onTranslationComplete("Çeviri başarısız oldu: ${e.message}")
        }
}

private fun startTranslation(translator: Translator, sourceText: String, onTranslationComplete: (String) -> Unit) {
    translator.translate(sourceText)
        .addOnSuccessListener { translatedText ->

            onTranslationComplete(translatedText)
        }
        .addOnFailureListener { e ->

            onTranslationComplete("Çeviri başarısız oldu: ${e.message}")
        }
}

@Composable
fun CommonIconButton(
    imageVector: ImageVector
) {

    Box(
        modifier = Modifier
            .background(Yellow, CircleShape)
            .size(33.dp), contentAlignment = Center
    ) {
        IconComponentImageVector(icon = imageVector, size = 15.dp, tint = Color.Black)
    }

}

