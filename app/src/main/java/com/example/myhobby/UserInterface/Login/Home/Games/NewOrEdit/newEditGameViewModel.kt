package com.example.myhobby.UserInterface.Login.Home.Games.NewOrEdit

import androidx.lifecycle.ViewModel
import com.example.myhobby.Database.gameEntryProps
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.GameGuardian

class newEditGameViewModel: ViewModel() {
    fun addGame(gameEntryProps: gameEntryProps){
        FirebaseDB.addGame(gameEntryProps)
    }
    fun updateGame(gameEntryProps: gameEntryProps){
        GameGuardian.game!!.name = gameEntryProps.name
        GameGuardian.game!!.publisher = gameEntryProps.publisher
        GameGuardian.game!!.released = gameEntryProps.released
        GameGuardian.game!!.description = gameEntryProps.description
        GameGuardian.game!!.icon = gameEntryProps.icon
        FirebaseDB.updateGame(GameGuardian.game!!)
    }
}