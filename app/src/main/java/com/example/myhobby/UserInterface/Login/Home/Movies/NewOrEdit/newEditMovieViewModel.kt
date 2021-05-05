package com.example.myhobby.UserInterface.Login.Home.Movies.NewOrEdit

import androidx.lifecycle.ViewModel
import com.example.myhobby.Database.movieEntryProps
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.MovieGuardian

class newEditMovieViewModel : ViewModel() {
    fun addMovie(movieEntryProps: movieEntryProps){
        FirebaseDB.addMovie(movieEntryProps)
    }
    fun updateMovie(movieEntryProps: movieEntryProps){
        MovieGuardian.movie!!.name = movieEntryProps.name
        MovieGuardian.movie!!.director = movieEntryProps.director
        MovieGuardian.movie!!.released = movieEntryProps.released
        MovieGuardian.movie!!.duration = movieEntryProps.duration
        MovieGuardian.movie!!.description = movieEntryProps.description
        MovieGuardian.movie!!.icon = movieEntryProps.icon
        FirebaseDB.updateMovie(MovieGuardian.movie!!)
    }
}