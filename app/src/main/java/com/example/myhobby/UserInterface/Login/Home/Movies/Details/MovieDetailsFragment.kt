package com.example.myhobby.UserInterface.Login.Home.Movies.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myhobby.R
import com.example.myhobby.Utils.GameGuardian
import com.example.myhobby.Utils.MovieGuardian

class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieIcon: ImageView = root.findViewById(R.id.movieIconDisplay)
        val editMovie: ImageView = root.findViewById(R.id.editMovieButton)
        val likeMovie: ImageButton = root.findViewById(R.id.likeMovieButton)
        val deleteMovie: ImageView = root.findViewById(R.id.deleteMovieButton)
        val name: TextView = root.findViewById(R.id.nameDisplay)
        val director: TextView = root.findViewById(R.id.directorDisplay)
        val released: TextView = root.findViewById(R.id.releaseDisplay)
        val description: TextView = root.findViewById(R.id.descriptionDisplay)
        val duration: TextView = root.findViewById(R.id.durationDisplay)

        if (MovieGuardian.movie?.icon?.isNotBlank()!!) {
            Glide.with(movieIcon)
                .load(MovieGuardian.movie?.icon)
                .into(movieIcon)
        } else {
            movieIcon.setImageResource(R.drawable.ic_movie)
        }

        name.setText(MovieGuardian.movie!!.name)
        director.setText(MovieGuardian.movie!!.director)
        released.setText(MovieGuardian.movie!!.released)
        description.setText(MovieGuardian.movie!!.description)
        duration.setText("Duration: ${MovieGuardian.movie!!.duration}")

        if (MovieGuardian.movie?.watched == true) {
            likeMovie.setImageResource(R.drawable.ic_fav_movie)
        } else {
            likeMovie.setImageResource(R.drawable.ic_not_fav_movie)
        }

        likeMovie.setOnClickListener {
            viewModel.changeMovieStatus()
            if (MovieGuardian.movie?.watched == true) {
                likeMovie.setImageResource(R.drawable.ic_fav_movie)
            } else {
                likeMovie.setImageResource(R.drawable.ic_not_fav_movie)
            }
        }

        deleteMovie.setOnClickListener {
            viewModel.deleteMovie()
            findNavController().popBackStack()
        }

        editMovie.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_newEditMovieFragment)
        }

        return root
    }
}