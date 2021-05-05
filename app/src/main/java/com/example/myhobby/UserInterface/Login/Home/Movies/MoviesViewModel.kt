package com.example.myhobby.UserInterface.Login.Home.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhobby.Database.movieEntry
import com.example.myhobby.Utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MoviesViewModel : ViewModel() {
    private val _allMoviesList = MutableLiveData<List<movieEntry>>()
    private val _selectedMoviesList = MutableLiveData<List<movieEntry>>()
    private val _moviesToLiveData = MutableLiveData<List<movieEntry>>()

    init {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tmpMovies: MutableList<movieEntry> = mutableListOf()
                for (movie in snapshot.children) {
                    tmpMovies.add(
                        movieEntry(
                            movie.child("name").getValue<String>() ?: "",
                            movie.child("director").getValue<String>() ?: "",
                            movie.child("released").getValue<String>() ?: "",
                            movie.child("description").getValue<String>() ?: "",
                            movie.child("duration").getValue<String>() ?: "",
                            movie.child("icon").getValue<String>() ?: "",
                            movie.child("watched").getValue<Boolean>() ?: false,
                            movie.child("id").getValue<String>() ?: ""
                        )
                    )
                }

                _allMoviesList.value = tmpMovies
                _moviesToLiveData.value = _allMoviesList.value
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FirebaseUtils.firebaseDatabase.getReference("movies").addValueEventListener(postListener)
    }

    fun getSelectedMovies(text: String = "", status: Int = 0) {
        if (text.isBlank() && status == 0) _selectedMoviesList.value = _allMoviesList.value
        else {
            if (!_allMoviesList.value.isNullOrEmpty()) {
                var tmpMovies: MutableList<movieEntry> = mutableListOf()
                if (text.isBlank()) _selectedMoviesList.value = _allMoviesList.value
                else {
                    for (movie in _allMoviesList.value!!) {
                        if (movie.name.toLowerCase().contains(text.toLowerCase())) tmpMovies.add(
                            movie
                        )
                    }
                    _selectedMoviesList.value = tmpMovies
                }

                if (status == 1 && !_selectedMoviesList.value.isNullOrEmpty()) {
                    var tmpMovies: MutableList<movieEntry> = mutableListOf()
                    for (movie in _selectedMoviesList.value!!) if (movie.watched) tmpMovies.add(
                        movie
                    )
                    _selectedMoviesList.value = tmpMovies
                }
                if (status == 2 && !_allMoviesList.value.isNullOrEmpty()) {
                    var tmpMovies: MutableList<movieEntry> = mutableListOf()
                    for (movie in _selectedMoviesList.value!!) if (!movie.watched) tmpMovies.add(
                        movie
                    )
                    _selectedMoviesList.value = tmpMovies
                }
            }
        }
        _moviesToLiveData.value = _selectedMoviesList.value
    }

    val moviesList: LiveData<List<movieEntry>> = _moviesToLiveData
}