package com.example.myhobby.UserInterface.Login.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myhobby.Database.gameEntry
import com.example.myhobby.Database.gameEntryProps
import com.example.myhobby.Database.movieEntryProps
import com.example.myhobby.R
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.FirebaseUtils
import java.util.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val gamesButton: LinearLayout = root.findViewById(R.id.gamesButton)
        val moviesButton: LinearLayout = root.findViewById(R.id.moviesButton)
        val seriesesButton: LinearLayout = root.findViewById(R.id.seriesButton)
        val booksButton: LinearLayout = root.findViewById(R.id.booksButton)
        val mainBanner: TextView = root.findViewById(R.id.mainBanner)

        mainBanner.setOnClickListener {
            FirebaseDB.addMovie(
                movieEntryProps(
                    "The Green Mile",
                    "Frank Darabont",
                    "10.12.1999",
                    "to be added later",
                    "3h09m",
                    "https://upload.wikimedia.org/wikipedia/en/e/e2/The_Green_Mile_%28movie_poster%29.jpg"
                )
            )
        }

        gamesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_gamesFragment)
        }
        moviesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
        }
        seriesesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_seriesesFragment)
        }
        booksButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_booksFragment)
        }

        return root
    }
}