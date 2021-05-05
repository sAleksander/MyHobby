package com.example.myhobby.Utils

import com.example.myhobby.Database.gameEntry
import com.example.myhobby.Database.gameEntryProps
import com.example.myhobby.Database.movieEntry
import com.example.myhobby.Database.movieEntryProps
import java.util.*

object FirebaseDB {
    val GAME_TABLE = "games"
    val MOVIE_TABLE = "movies"

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

    fun addMovie(movieEntryProps: movieEntryProps) {
        val movieId = generateId("m")
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(MOVIE_TABLE).child(movieId)
        val newMovieEntry = movieEntry(
            movieEntryProps.name,
            movieEntryProps.director,
            movieEntryProps.released,
            movieEntryProps.description,
            movieEntryProps.duration,
            movieEntryProps.icon,
            movieEntryProps.watched,
            movieId
        )
        reference.setValue(newMovieEntry)
    }

    fun updateMovie(movie: movieEntry) {
        val movieId = movie.id
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(MOVIE_TABLE).child(movieId)
        reference.setValue(movie)
    }

    fun deleteMovie(movieId: String) {
        val reference =
            FirebaseUtils.firebaseDatabase.getReference(MOVIE_TABLE).child(movieId)
        reference.removeValue()
    }

}