package com.example.myhobby.UserInterface.Login.Home.Games.Details

import androidx.lifecycle.ViewModel
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.GameGuardian

class GameDetailsViewModel : ViewModel() {
    fun changeGameStatus() {
        GameGuardian.game!!.played = !GameGuardian.game!!.played
        FirebaseDB.updateGame(GameGuardian.game!!)
    }
}