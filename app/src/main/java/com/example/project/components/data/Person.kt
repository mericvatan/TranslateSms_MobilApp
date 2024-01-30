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
                "Merhaba!",
                "17:30",
                true
            ),
            Chat(
                2,
                "Müsait misin?",
                "17:31",
                true
            ),
            Chat(
                3,
                "Evet müsaitim",
                "17:35",
                false
            ),
        ),
        "Evet müsaitim",
        "17:17"
    ),
    Person(
        2,
        "Tuğba",
        R.drawable.person_2,
        listOf(
            Chat(
                1,
                "Selam!",
                "16:30",
                true
            ),
            Chat(
                2,
                "Dışarı çıkalım mı?",
                "16:32",
                true
            ),
            Chat(
                3,
                "Harika olur, hadi gidelim",
                "16:35",
                false
            ),
        ),
        "Harika olur",
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
                "Film izlemeye gidelim mi?",
                "14:16",
                true
            ),
            Chat(
                3,
                "Olabilir, aklında hangi film var?",
                "14:17",
                false
            ),
        ),
        "Olabilir, aklında hangi film var?",
        "14:17"
    ),
    Person(
        4,
        "Selen",
        R.drawable.person_4,
        listOf(
            Chat(
                1,
                "Günaydın",
                "11:33",
                true
            ),
            Chat(
                2,
                "Bugün derse gidiyor muyuz?",
                "11:34",
                true
            ),
            Chat(
                3,
                "Evet, şimdi çıkıyorum",
                "11:35",
                false
            ),
        ),
        "Evet, şimdi çıkıyorum",
        "11:35"
    ),
    Person(
        5,
        "Özge",
        R.drawable.person_5,
        listOf(
            Chat(
                1,
                "Sınav notları açıklanmış",
                "11:30",
                true
            ),
            Chat(
                2,
                "Gördün mü?",
                "11:31",
                true
            ),
            Chat(
                3,
                "Hayır, hemen bakıyorum",
                "11:32",
                false
            ),

        ),
        "Hayır, hemen bakıyorum",
        "11:32"
    )
)
