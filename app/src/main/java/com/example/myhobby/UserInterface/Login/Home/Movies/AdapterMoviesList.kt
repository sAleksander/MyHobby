package com.example.myhobby.UserInterface.Login.Home.Movies

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhobby.Database.movieEntry
import com.example.myhobby.R
import com.example.myhobby.Utils.MovieGuardian

class AdapterMoviesList(
    var moviesList: LiveData<List<movieEntry>>,
    private val onClick: (() -> Unit)
) : RecyclerView.Adapter<AdapterMoviesList.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView:  TextView = itemView.findViewById(R.id.nameDisplay)
        val favStatus:  ImageView = itemView.findViewById(R.id.favStatus)
        val durationView:  TextView = itemView.findViewById(R.id.durationDisplay)
        val icon: ImageView = itemView.findViewById(R.id.movieIconDisplay)

        init {
            itemView.setOnClickListener {
                if (!moviesList.value.isNullOrEmpty()){
                    MovieGuardian.movie = moviesList.value!!.get(adapterPosition)
                    onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = moviesList.value?.get(position)
        if (currentItem != null){
            holder.nameView.setText(currentItem.name.toString())
            holder.durationView.setText(currentItem.duration.toString())
            if(currentItem.watched) holder.favStatus.setImageResource(R.drawable.ic_fav_movie)
            else holder.favStatus.setImageResource(R.drawable.ic_not_fav_movie)

            if (currentItem.icon.isNotBlank()){
                Glide.with(holder.icon.context)
                    .load(currentItem.icon)
                    .into(holder.icon)
            } else holder.icon.setImageResource(R.drawable.ic_movie)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.value?.size ?: 0
    }
}