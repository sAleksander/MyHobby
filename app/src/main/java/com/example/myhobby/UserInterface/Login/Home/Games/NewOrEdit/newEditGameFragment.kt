package com.example.myhobby.UserInterface.Login.Home.Games.NewOrEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myhobby.R
class newEditGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_new_edit_game, container, false)
        val errorMessage: TextView = root.findViewById(R.id.errorMessageDisplay)
        val operationButton: Button = root.findViewById(R.id.operationButton)

        operationButton.setOnClickListener {
            errorMessage.visibility = View.VISIBLE
        }


        return root
    }
}