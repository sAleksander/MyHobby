package com.example.myhobby.Database

import java.util.*

data class gameEntry(
    var name: String,
    var publisher: String,
    var released: String,
    var description: String,
    var icon: String,
    var played: Boolean = false,
    val id: String,
)

data class gameEntryProps(
    val name: String,
    val publisher: String,
    val released: String,
    val description: String,
    val icon: String,
    val played: Boolean = false,
)
