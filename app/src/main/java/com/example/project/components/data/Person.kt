package com.example.project.components.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.project.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Chat(
    val id: Int,
    val message: String,
    val time: String,
    val direction: Boolean
) : Parcelable {
    companion object {
        val chatList = mutableListOf<Chat>()
    }
}

@Parcelize
data class Person(
    val id: Int = 0,
    val name: String = "",
    @DrawableRes val icon: Int = R.drawable.person_1,
    val chatList: List<@kotlin.jvm.JvmSuppressWildcards @RawValue Chat> = emptyList(),
    val status: String = "",
    val time: String = ""
) : Parcelable

val personList = listOf(
    Person(
        1,
        "Şevval",
        R.drawable.person_1,
        listOf(
            Chat(
                1,
                "Merhaba, müsait misin",
                "17:30",
                true
            ),
            Chat(
                2,
                "Sana bir şey soracaktım",
                "17:31",
                true
            ),
            Chat(
                3,
                "Evet müsaitim",
                "17:35",
                false
            ),
            Chat(
                4,
                "Dün konuştuğumuz konu aklıma takıldı, tekrar konuşalım mı?",
                "17:37",
                true
            ),
            Chat(
                5,
                "Benim de öyle, konuşalım",
                "17:38",
                false
            ),
        ),
        "Benim de öyle, konuşalım",
        "17:38"
    ),
    Person(
        2,
        "Tuğba",
        R.drawable.person_2,
        listOf(
            Chat(
                1,
                "Selam",
                "16:30",
                true
            ),
            Chat(
                2,
                "Ne düşünüyorum biliyor musun",
                "16:32",
                true
            ),
            Chat(
                2,
                "Alışveriş yapmaya gitmemiz lazım",
                "16:32",
                true
            ),
            Chat(
                3,
                "Aklımı okuyorsun ",
                "16:35",
                false
            ),
        ),
        "Aklımı okuyorsun",
        "16:35"
    ),
    Person(
        3,
        "Zehra",
        R.drawable.person_3,
        listOf(
            Chat(
                1,
                "Selam, bu akşam ne yapıyorsun?",
                "14:15",
                true
            ),
            Chat(
                2,
                "Yeni çıkan şu filmi bu akşam izlemeye gitsek diyorum",
                "14:16",
                true
            ),
            Chat(
                3,
                "Çok eğleniriz, ne dersin?",
                "14:17",
                true
            ),
            Chat(
                4,
                "Harika olur gerçekten",
                "14:20",
                false
            ),
        ),
        "Harika olur gerçekten",
        "14:20"
    ),
    Person(
        4,
        "Selen",
        R.drawable.person_4,
        listOf(
            Chat(
                1,
                "Günaydın, hava çok güzel",
                "11:33",
                true
            ),
            Chat(
                2,
                "Bugün derse gidiyor musun? Biraz geride kaldık",
                "11:34",
                true
            ),

            Chat(
                3,
                "Evet, gidiyorum sen?",
                "11:35",
                false
            ),
        ),
        "Evet, gidiyorum sen?",
        "11:35"
    ),
    Person(
        5,
        "Özge",
        R.drawable.person_5,
        listOf(
            Chat(
                1,
                "Sana neler olduğunu anlattığımda inanmayacaksın",
                "11:30",
                true
            ),
            Chat(
                2,
                "Çok şey kaçırdın, hemen buluşmalıyız",
                "11:31",
                true
            ),
            Chat(
                3,
                "Hemen anlatmalısın bana",
                "11:32",
                false
            ),

        ),
        "Hemen anlatmalısın",
        "11:32"
    )
)
