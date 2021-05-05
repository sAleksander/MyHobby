package com.example.myhobby.UserInterface.Login.Home.Games.NewOrEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myhobby.Database.gameEntryProps
import com.example.myhobby.R
import com.example.myhobby.Utils.FirebaseDB
import com.example.myhobby.Utils.GameGuardian

class newEditGameFragment : Fragment() {

    private lateinit var viewModel: newEditGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(newEditGameViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_edit_game, container, false)
        val errorMessage: TextView = root.findViewById(R.id.errorMessageDisplay)
        val nameEntry: EditText = root.findViewById(R.id.nameEntry)
        val publisherEntry: EditText = root.findViewById(R.id.publisherEntry)
        val releaseEntry: EditText = root.findViewById(R.id.releaseEntry)
        val urlEntry: EditText = root.findViewById(R.id.urlEntry)
        val descriptionEntry: EditText = root.findViewById(R.id.descriptionEntry)
        val operationButton: Button = root.findViewById(R.id.operationButton)

        val required1: TextView = root.findViewById(R.id.required1)
        val required2: TextView = root.findViewById(R.id.required2)
        val required3: TextView = root.findViewById(R.id.required3)
        val requiredDate: TextView = root.findViewById(R.id.requiredDate)

        if (GameGuardian.game == null) {
            operationButton.text = "Add game"
        } else {
            operationButton.text = "Update game"
            nameEntry.setText(GameGuardian.game!!.name)
            publisherEntry.setText(GameGuardian.game!!.publisher)
            releaseEntry.setText(GameGuardian.game!!.released)
            urlEntry.setText(GameGuardian.game!!.icon)
            descriptionEntry.setText(GameGuardian.game!!.description)
        }

        operationButton.setOnClickListener {
            if (
                nameEntry.text.isBlank() ||
                publisherEntry.text.isBlank() ||
                releaseEntry.text.isBlank() ||
                descriptionEntry.text.isBlank()
            ) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText("Fill required fields!")
                required1.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                required2.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                required3.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                requiredDate.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
            } else if (!releaseEntry.text.matches(Regex("\\d\\d.\\d\\d.\\d\\d\\d\\d"))) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText("Wrong date format!")
                requiredDate.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
            }
            else{
                val gameEntryProps = gameEntryProps(
                    nameEntry.text.toString(),
                    publisherEntry.text.toString(),
                    releaseEntry.text.toString(),
                    descriptionEntry.text.toString(),
                    urlEntry.text.toString(),
                )
                if (GameGuardian.game == null){
                    viewModel.addGame(gameEntryProps)
                }
                else{
                    viewModel.updateGame(gameEntryProps)
                }
                findNavController().popBackStack()
            }
        }

        return root
    }
}