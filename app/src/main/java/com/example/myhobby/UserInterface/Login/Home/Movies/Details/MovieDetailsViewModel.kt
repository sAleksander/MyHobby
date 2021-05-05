package com.example.myhobby.UserInterface.Login.Home.Movies.Details

import androidx.lifecycle.ViewModel
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.MovieGuardian

class MovieDetailsViewModel : ViewModel() {
    fun changeMovieStatus() {
        MovieGuardian.movie!!.watched = !MovieGuardian.movie!!.watched
        FirebaseDB.updateMovie(MovieGuardian.movie!!)
    }

    fun deleteMovie() {
        MovieGuardian.movie?.let { FirebaseDB.deleteMovie(it.id) }
    }
}