package com.example.myhobby.UserInterface.Login.Home.Movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhobby.R
import com.example.myhobby.UserInterface.Login.Home.Games.AdapterGamesList
import com.example.myhobby.Utils.MovieGuardian

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var myAdapter: AdapterMoviesList
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        val searchInput: EditText = root.findViewById(R.id.searchForNameInput)
        val clearSearch: ImageView = root.findViewById(R.id.clearSearchButton)
        val addMovieButton: ImageView = root.findViewById(R.id.addMovieButton)
        val allButton: Button = root.findViewById(R.id.allMoviesButton)
        val watchedButton: Button = root.findViewById(R.id.watchedMoviesButton)
        val notWatchedButton: Button = root.findViewById(R.id.notWatchedMoviesButton)
        var status = 0
        val innerClick = {
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment)
        }

        MovieGuardian.movie = null

        myLayoutManager = LinearLayoutManager(context)
        myAdapter = AdapterMoviesList(viewModel.moviesList,innerClick)

        viewModel.moviesList.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        clearSearch.setOnClickListener {
            searchInput.setText("")
        }

        searchInput.addTextChangedListener {
            viewModel.getSelectedMovies(searchInput.text.toString(),status)
        }

        allButton.setOnClickListener {
            status = 0
            viewModel.getSelectedMovies(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.healthyGreen)
            watchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            notWatchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
        }
        watchedButton.setOnClickListener {
            status = 1
            viewModel.getSelectedMovies(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            watchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.healthyGreen)
            notWatchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
        }
        notWatchedButton.setOnClickListener {
            status = 2
            viewModel.getSelectedMovies(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            watchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            notWatchedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.healthyGreen)
        }
        addMovieButton.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_newEditMovieFragment)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesRecycler: RecyclerView = view.findViewById(R.id.moviesRecycler)

        recyclerView = moviesRecycler.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

}