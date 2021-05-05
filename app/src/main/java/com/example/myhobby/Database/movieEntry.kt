package com.example.myhobby.Database

data class movieEntry(
    var name: String,
    var director: String,
    var released: String,
    var description: String,
    var duration: String,
    var icon: String,
    var watched: Boolean = false,
    val id: String,
)

data class movieEntryProps(
    val name: String,
    val director: String,
    val released: String,
    val description: String,
    val duration: String,
    val icon: String,
    val watched: Boolean = false,
)