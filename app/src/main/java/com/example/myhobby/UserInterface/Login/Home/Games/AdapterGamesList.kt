package com.example.myhobby.UserInterface.Login.Home.Games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myhobby.Database.gameEntry
import com.example.myhobby.R

class AdapterGamesList(
    var gamesList: LiveData<List<gameEntry>>,
    private val onClick: ((position: Int) -> Unit)?,
) : RecyclerView.Adapter<AdapterGamesList.GameViewHolder>() {
    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.nameDisplay)
        val favStatus: ImageView = itemView.findViewById(R.id.favStatus)
        val icon: ImageView = itemView.findViewById(R.id.gameIconDisplay)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = gamesList.value?.get(position)
        if (currentItem != null) {
            holder.textView.setText(currentItem.name.toString())
            if (currentItem.played) holder.favStatus.setImageResource(R.drawable.ic_fav)
            else holder.favStatus.setImageResource(R.drawable.ic_not_fav)
        }
    }

    override fun getItemCount(): Int {
        return gamesList.value?.size ?: 0
    }


}