package com.example.myhobby.Database

import java.util.*

data class gameEntry(
    val name: String,
    val publisher: String,
    val released: String,
    val description: String,
    val icon: String,
    val played: Boolean = false,
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
