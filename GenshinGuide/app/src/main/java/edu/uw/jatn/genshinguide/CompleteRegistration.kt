package edu.uw.jatn.genshinguide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import edu.uw.jatn.genshinguide.databinding.FragmentCompleteRegistrationBinding
import edu.uw.jatn.genshinguide.databinding.FragmentRegisterBinding


class CompleteRegistration : AppCompatActivity() {

    private lateinit var binding: FragmentCompleteRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentCompleteRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener{ view ->
            val intent = intent
            val new_extras = Bundle()
            val extras = intent.extras
            val username = extras?.getString("USERNAME")
            val password = extras?.getString("PASSWORD")
            val goHome = Intent(this, MainActivity::class.java)

            new_extras.putString("USERNAME", username)
            new_extras.putString("PASSWORD", password)
            goHome.putExtras(new_extras)
            startActivity(goHome)
        }
    }
}