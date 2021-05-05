package com.example.myhobby.UserInterface.Login.Home.Movies.NewOrEdit

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
import com.example.myhobby.Database.movieEntryProps
import com.example.myhobby.R
import com.example.myhobby.UserInterface.Login.Home.Games.NewOrEdit.newEditGameViewModel
import com.example.myhobby.Utils.MovieGuardian

class newEditMovieFragment : Fragment() {

    private lateinit var viewModel: newEditMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(newEditMovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_edit_movie, container, false)
        val errorMessage: TextView = root.findViewById(R.id.errorMessageDisplay)
        val nameEntry: EditText = root.findViewById(R.id.nameEntry)
        val directorEntry: EditText = root.findViewById(R.id.directorEntry)
        val releasedEntry: EditText = root.findViewById(R.id.releaseEntry)
        val descriptionEntry: EditText = root.findViewById(R.id.descriptionEntry)
        val durationEntry: EditText = root.findViewById(R.id.durationEntry)
        val iconEntry: EditText = root.findViewById(R.id.urlEntry)

        val operationButton: Button = root.findViewById(R.id.operationButton)

        val required1: TextView = root.findViewById(R.id.required1)
        val required2: TextView = root.findViewById(R.id.required2)
        val required3: TextView = root.findViewById(R.id.required3)
        val requiredDate: TextView = root.findViewById(R.id.requiredDate)
        val requiredDuration: TextView = root.findViewById(R.id.requiredDuration)

        if (MovieGuardian.movie == null) {
            operationButton.text = "Add movie"
        } else {
            operationButton.text = "Update movie"
            nameEntry.setText(MovieGuardian.movie!!.name)
            directorEntry.setText(MovieGuardian.movie!!.director)
            releasedEntry.setText(MovieGuardian.movie!!.released)
            descriptionEntry.setText(MovieGuardian.movie!!.description)
            durationEntry.setText(MovieGuardian.movie!!.duration)
            iconEntry.setText(MovieGuardian.movie!!.icon)
        }

        operationButton.setOnClickListener {
            required1.setBackgroundColor(resources.getColor(R.color.healthyGreen))
            required2.setBackgroundColor(resources.getColor(R.color.healthyGreen))
            required3.setBackgroundColor(resources.getColor(R.color.healthyGreen))
            requiredDate.setBackgroundColor(resources.getColor(R.color.healthyGreen))
            requiredDuration.setBackgroundColor(resources.getColor(R.color.healthyGreen))
            if (
                nameEntry.text.isBlank() ||
                directorEntry.text.isBlank() ||
                releasedEntry.text.isBlank() ||
                descriptionEntry.text.isBlank() ||
                durationEntry.text.isBlank()
            ) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText("Fill required fields!")
                required1.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                required2.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                required3.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                requiredDate.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
                requiredDuration.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
            } else if (!releasedEntry.text.matches(Regex("\\d\\d.\\d\\d.\\d\\d\\d\\d"))) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText("Wrong date format!")
                requiredDate.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
            } else if (!durationEntry.text.matches(Regex("\\dh\\d\\dm"))) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText("Wrong duration format!")
                requiredDuration.setBackgroundColor(resources.getColor(R.color.bloodlustRed))
            } else {
                val movieEntryProps = movieEntryProps(
                    nameEntry.text.toString(),
                    directorEntry.text.toString(),
                    releasedEntry.text.toString(),
                    descriptionEntry.text.toString(),
                    durationEntry.text.toString(),
                    iconEntry.text.toString(),
                )
                if (MovieGuardian.movie == null) {
                    viewModel.addMovie(movieEntryProps)
                } else {
                    viewModel.updateMovie(movieEntryProps)
                }
                findNavController().popBackStack()
            }
        }

        return root
    }
}