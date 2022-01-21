/** Jason Nguyen: I wrote the methods for the login page and created a landscape view in this
 * Activity. It utilizes shared preferences to keep track of the user's data like name and password
 * for future application uses
 **/
package edu.uw.jatn.genshinguide


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import edu.uw.jatn.genshinguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // When the user clicks on the login button, the name and password they inputted will be
        // checked and validated.
        binding.loginbutton.setOnClickListener { view ->
            val username = findViewById<EditText>(R.id.username).text.toString()
            val userPassword = findViewById<EditText>(R.id.password).text.toString()

            val sharedPref =
                this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

            val name = sharedPref.getString(resources.getString(R.string.username), "")
            val password = sharedPref.getString(resources.getString(R.string.password), "")

            validate(username, name, password, userPassword)
        }

        // When the user clicks on the signup button, they will be sent to the register page to
        // create an account.
        binding.signup.setOnClickListener { view ->
            val goToRegister = Intent(this, Register::class.java)
            startActivity(goToRegister)
        }
    }

    // A method that verifies the name and password given by the user. If the user has not made an
    // account, it will fail to login. If there are mismatches with the name and password, login will
    // fail. Both name and password are case sensitive.
    private fun validate(name: String, user: String?, password: String?, userPassword: String) {

        if ((name == user) and (userPassword == password)) {
            val goToGuideActivity = Intent(this, GuideActivity::class.java)
            startActivity(goToGuideActivity)
        } else {
            Toast.makeText(applicationContext, "Login failed, please try again", Toast.LENGTH_LONG)
                .show()
        }
    }
}