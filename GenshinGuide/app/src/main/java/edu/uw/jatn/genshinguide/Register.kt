/** Jason Nguyen: I created the registration activity where users can create an account and it'll
 be used to access the application. I incorporated shared preferences and built support for
 landscape mode.
 **/
package edu.uw.jatn.genshinguide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.uw.jatn.genshinguide.databinding.FragmentRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: FragmentRegisterBinding

    private val USERNAME = "USERNAME" // Key for storing and pulling the name
    private val PASSWORD = "PASSWORD" // Key for storing and pulling the password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // When user clicks on the register button, they will register their account to be able to
        // log into the application.
        binding.registerbutton.setOnClickListener { view ->
            val username = findViewById<EditText>(R.id.nickname).text.toString()
            val password1 = findViewById<EditText>(R.id.password1).text.toString()
            val password2 = findViewById<EditText>(R.id.password2).text.toString()
            val goToRegister = Intent(applicationContext, CompleteRegistration::class.java)
            val extras = Bundle()

            // Checks if the password inputs are the same. If it is NOT the same, the user will
            // receive an "error" message. If they are the same, the user will be validated and can
            // login to the application. If there was any prior account, it will clear the data in
            // shared preferences.
            if (password1 == password2) {
                clearData()
                val confirmedPassword = password2
                extras.putString(USERNAME, username)
                extras.putString(PASSWORD, confirmedPassword)
                goToRegister.putExtras(extras)
                saveData(username, confirmedPassword)
                startActivity(goToRegister)
            } else {
                Toast.makeText(applicationContext, "Password does not match", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    // Saves and stores the username and password into shared preferences
    private fun saveData(name: String, password: String) {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString(getString(R.string.username), name)
            putString(getString(R.string.password), password)
            apply()
        }
    }

    // Clears data within shared preferences if user creates a new account.
    private fun clearData() {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.edit().clear().commit()
    }

}