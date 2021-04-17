package com.example.myhobby.UserInterface.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.myhobby.R
import com.example.myhobby.Utils.FirebaseUtils

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton: Button = root.findViewById(R.id.loginButton)
        val emailInput: TextView = root.findViewById(R.id.emailInput)
        val passwordInput: TextView = root.findViewById(R.id.passwordInput)

        loginButton.setOnClickListener {
            if (emailInput.text.isNotBlank() && passwordInput.text.isNotBlank()) {
                loginButton.isEnabled = false
                FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment)
                    else {
                        emailInput.setText("")
                        emailInput.setHint("Wrong email!")
                        passwordInput.setText("")
                        passwordInput.setHint("Wrong password!")
                    }
                    loginButton.isEnabled = true
                }
            }
        }

        return root
    }
}