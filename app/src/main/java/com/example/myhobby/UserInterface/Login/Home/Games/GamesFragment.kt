package com.example.myhobby.UserInterface.Login.Home.Games

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhobby.R

class GamesFragment : Fragment() {

    private lateinit var viewModel: GamesViewModel
    private lateinit var myAdapter: AdapterGamesList
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GamesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_games, container, false)
        val searchInput: EditText = root.findViewById(R.id.searchForNameInput)
        val acceptSearch: ImageView = root.findViewById(R.id.acceptSearchButton)
        val addGameButton: ImageView = root.findViewById(R.id.addGameButton)
        val allButton: Button = root.findViewById(R.id.allGamesButton)
        val playedButton: Button = root.findViewById(R.id.playedGamesButton)
        val unplayedButton: Button = root.findViewById(R.id.unPlayedGamesButton)
        var status = 0
        val innerClick = {position: Int ->
            findNavController().navigate(R.id.action_gamesFragment_to_gameDetailsFragment)
        }

        myLayoutManager = LinearLayoutManager(context)
        myAdapter = AdapterGamesList(viewModel.gamesList,innerClick)

        viewModel.gamesList.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        acceptSearch.setOnClickListener {
            viewModel.getSelectedGames(searchInput.text.toString(),status)
        }

        allButton.setOnClickListener{
            status = 0
            viewModel.getSelectedGames(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.soringOrange)
            playedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            unplayedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
        }
        playedButton.setOnClickListener{
            status = 1
            viewModel.getSelectedGames(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            playedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.soringOrange)
            unplayedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
        }
        unplayedButton.setOnClickListener{
            status = 2
            viewModel.getSelectedGames(searchInput.text.toString(),status)
            allButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            playedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.cardPurple)
            unplayedButton.backgroundTintList = context?.resources?.getColorStateList(R.color.soringOrange)
        }
        addGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_gameDetailsFragment)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gamesRecycler: RecyclerView = view.findViewById(R.id.gamesRecycler)

        recyclerView = gamesRecycler.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }
}
