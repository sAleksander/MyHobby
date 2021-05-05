package com.example.myhobby.UserInterface.Login.Home.Games.Details

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

class GameDetailsFragment : Fragment() {

    private lateinit var viewModel: GameDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(GameDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_game_details, container, false)
        val gameIcon: ImageView = root.findViewById(R.id.gameIconDisplay)
        val editGame: ImageView = root.findViewById(R.id.editGameButton)
        val likeGame: ImageButton = root.findViewById(R.id.likeGameButton)
        val deleteGame: ImageView = root.findViewById(R.id.deleteGameButton)
        val name: TextView = root.findViewById(R.id.nameDisplay)
        val publisher: TextView = root.findViewById(R.id.publisherDisplay)
        val release: TextView = root.findViewById(R.id.releaseDisplay)
        val description: TextView = root.findViewById(R.id.descriptionDisplay)

        if (GameGuardian.game?.icon?.isNotBlank()!!) {
            Glide.with(gameIcon)
                .load(GameGuardian.game?.icon)
                .into(gameIcon)
        } else {
            gameIcon.setImageResource(R.drawable.ic_game)
        }

        name.setText(GameGuardian.game!!.name)
        publisher.setText("by ${GameGuardian.game!!.publisher}")
        release.setText(GameGuardian.game!!.released)
        description.setText(GameGuardian.game!!.description)

        if (GameGuardian.game?.played == true) {
            likeGame.setImageResource(R.drawable.ic_fav_game)
        } else {
            likeGame.setImageResource(R.drawable.ic_not_fav_game)
        }

        likeGame.setOnClickListener {
            viewModel.changeGameStatus()
            if (GameGuardian.game?.played == true) {
                likeGame.setImageResource(R.drawable.ic_fav_game)
            } else {
                likeGame.setImageResource(R.drawable.ic_not_fav_game)
            }
        }

        deleteGame.setOnClickListener {
            viewModel.deleteGame()
            findNavController().popBackStack()
        }

        editGame.setOnClickListener {
           findNavController().navigate(R.id.action_gameDetailsFragment_to_newEditGameFragment)
        }

        return root
    }
}