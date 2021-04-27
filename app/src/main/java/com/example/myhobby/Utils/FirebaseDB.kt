package com.example.myhobby.Utils

import com.example.myhobby.Database.gameEntry
import com.example.myhobby.Database.gameEntryProps
import java.util.*

object FirebaseDB {
    val GAME_TABLE = "games"

    fun generateId(type: String): String {
        return "${type}${Date().time}${(1..1000).random()}"
    }

    fun addGame(gameEntryProps: gameEntryProps) {
        val gameId = generateId("g")
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(GAME_TABLE).child(gameId)
        val newGameEntry = gameEntry(
            gameEntryProps.name,
            gameEntryProps.publisher,
            gameEntryProps.released,
            gameEntryProps.description,
            gameEntryProps.icon,
            gameEntryProps.played,
            gameId
        )
        reference.setValue(newGameEntry)
    }

    fun updateGame(game: gameEntry) {
        val gameId = game.id
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(GAME_TABLE).child(gameId)
        reference.setValue(game)
    }

    fun deleteGame(gameId: String) {
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(GAME_TABLE).child(gameId)
        reference.removeValue()
    }
}