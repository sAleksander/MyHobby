package com.example.myhobby.UserInterface.Login.Home.Games

import android.util.Log
import android.widget.Switch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhobby.Database.gameEntry
import com.example.myhobby.Utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class GamesViewModel : ViewModel() {

    private val _allGamesList = MutableLiveData<List<gameEntry>>()
    private val _selectedGamesList = MutableLiveData<List<gameEntry>>()
    private val _gamesToLiveData = MutableLiveData<List<gameEntry>>()

    init {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tmpGames: MutableList<gameEntry> = mutableListOf()
                for (game in snapshot.children) {
                    tmpGames.add(
                        gameEntry(
                            game.child("name").getValue<String>() ?: "",
                            game.child("publisher").getValue<String>() ?: "",
                            game.child("released").getValue<String>() ?: "",
                            game.child("description").getValue<String>() ?: "",
                            game.child("played").getValue<Boolean>() ?: false,
                            game.child("id").getValue<String>() ?: "",
                        )
                    )
                }

                _allGamesList.value = tmpGames
                _gamesToLiveData.value = _allGamesList.value
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FirebaseUtils.firebaseDatabase.getReference("games").addValueEventListener(postListener)
    }

    fun getGamesByName(text: String) {
        if (text.isBlank()) _gamesToLiveData.value = _allGamesList.value
        else {
            if (!_allGamesList.value.isNullOrEmpty()) {
                var tmpGames: MutableList<gameEntry> = mutableListOf()
                for (game in _allGamesList.value!!) {
                    if (game.name == text) tmpGames.add(game)
                }
                _selectedGamesList.value = tmpGames
                _gamesToLiveData.value = _selectedGamesList.value
            }
        }
    }

    fun getSelectedGames(text: String = "", status: Int = 0) {
        if (text.isBlank() && status == 0) _selectedGamesList.value = _allGamesList.value
        else {
            if (!_allGamesList.value.isNullOrEmpty()) {
                var tmpGames: MutableList<gameEntry> = mutableListOf()
                if (text.isBlank()) _selectedGamesList.value = _allGamesList.value
                else {
                    for (game in _allGamesList.value!!) {
                        if (game.name == text) tmpGames.add(game)
                    }
                    _selectedGamesList.value = tmpGames
                }

                if (status == 1 && !_selectedGamesList.value.isNullOrEmpty()) {
                    var tmpGames: MutableList<gameEntry> = mutableListOf()
                    for (game in _selectedGamesList.value!!) if (game.played) tmpGames.add(game)
                    _selectedGamesList.value = tmpGames
                } else if (status == 2 && !_allGamesList.value.isNullOrEmpty()) {
                    var tmpGames: MutableList<gameEntry> = mutableListOf()
                    for (game in _selectedGamesList.value!!) if (!game.played) tmpGames.add(game)
                    _selectedGamesList.value = tmpGames
                }
            }
        }
        _gamesToLiveData.value = _selectedGamesList.value
    }
    // status == 0 -> all games
    // status == 1 -> played games
    // status == 2 -> unplayed games

    fun getGamesByStatus(status: Int) {
        if (status == 0) _gamesToLiveData.value = _allGamesList.value
        else if (status == 1 && !_allGamesList.value.isNullOrEmpty()) {
            var tmpGames: MutableList<gameEntry> = mutableListOf()
            for (game in _allGamesList.value!!) if (game.played) tmpGames.add(game)
            _gamesToLiveData.value = tmpGames
        } else if (status == 2 && !_allGamesList.value.isNullOrEmpty()) {
            var tmpGames: MutableList<gameEntry> = mutableListOf()
            for (game in _allGamesList.value!!) if (!game.played) tmpGames.add(game)
            _gamesToLiveData.value = tmpGames
        }
    }

    val gamesList: LiveData<List<gameEntry>> = _gamesToLiveData
}