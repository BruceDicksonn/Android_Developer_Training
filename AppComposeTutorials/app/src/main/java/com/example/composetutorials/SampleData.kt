package com.example.composetutorials

import androidx.compose.ui.res.painterResource

object SampleData {
    val Talk = listOf(
        Message(
            R.drawable.gendo,
            "Gendo Ikari",
            "Hello Shinji, enter on the EVA01 and fight!"
        ),

        Message(
            R.drawable.shinji,
            "Shinji Ikari",
            "EVA? What is an EVA?",
            0,
            true
        ),

        Message(
            R.drawable.gendo,
            "Gendo Ikari",
            "No questions asked, obey now!?"
        ),

        Message(
            R.drawable.shinji,
            "Shinji Ikari",
            "How can you think you rule me?",
            1,
            true
        ),

        Message(
            R.drawable.shinji,
            "Shinji Ikari",
            "You disappeared for fifteen years and never sent me a card, never wanted to talk to me",
            1,
            true
        ),
        Message(
            R.drawable.shinji,
            "Shinji Ikari",
            "I will not obey your orders. I want to go to my house and I will.",
            1,
            true
        ),

        Message(
            R.drawable.gendo,
            "Gendo Ikari",
            "You are a coward who only knows how to hide",
            1
        ),
        Message(
            R.drawable.gendo,
            "Gendo Ikari",
            "If you want to go, go, no one will stop you",
            1
        ),
        Message(
            R.drawable.gendo,
            "Gendo Ikari",
            "Go back to your pathetic, meaningless life.",
            1
        ),

    )
}