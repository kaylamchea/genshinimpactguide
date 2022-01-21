/** Jason Nguyen: I created the Profile page and implemented the permissions for camera. I also
 created a landscape mode for this activity and allowed the data to be saved. This page is made so
 that the user can update their information like bio description or profile picture.
 **/
package edu.uw.jatn.genshinguide

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.uw.jatn.genshinguide.databinding.FragmentProfileBinding
import java.io.ByteArrayOutputStream

class Profile : AppCompatActivity() {

    private val CAMERA_REQUEST = 101 // Code for accessing Camera permissions
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receives shared preferences to pull values for profile elements such as name, bio, and
        // profile picture
        val sharedPref =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)
        val guide = findViewById<FloatingActionButton>(R.id.guide)
        val image = findViewById<ImageView>(R.id.picture)
        val signout = findViewById<Button>(R.id.signout)

        // Receives name from shared preferences
        val name = sharedPref.getString(resources.getString(R.string.username), "")
        var username = findViewById<TextView>(R.id.name)

        // Receives description for the bio from shared preferences
        val description = sharedPref.getString(resources.getString(R.string.bio), "")

        var bio = findViewById<EditText>(R.id.description)

        // Receives image (in byte format) from shared preferences
        val byte = sharedPref.getString(resources.getString(R.string.image), "")
        val byteImage = Base64.decode(byte, Base64.DEFAULT)
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteImage, 0, byteImage.size))

        // Replace the text with the name from the shared preference
        username.text = name

        // Set the text with the bio description from shared preference
        bio.setText(description)

        // When user click on the ImageView, they will be asked to give permission to use the
        // camera. Once the user allows permission, they will have access to the camera to take
        // pictures to set as their profile picture. This picture will be stored, saved, and displayed
        image.setOnClickListener {
            askCameraPermission()
            Toast.makeText(this, "Camera activated", Toast.LENGTH_SHORT).show()
            saveData()
        }

        // When the user clicked on the sign out button, they will be sent back to the login page.
        // Any element such as bio description or profile picture will be saved.
        signout.setOnClickListener {
            val goToLogin = Intent(this, MainActivity::class.java)
            saveData()
            startActivity(goToLogin)
        }

        // When the user clicks on the guide icon, they will be sent to the guide activity page where
        // they can view characters from Genshin Impact. Elements from the profile page will be
        // saved.
        guide.setOnClickListener {
            val goToGuide = Intent(this, GuideActivity::class.java)
            saveData()
            startActivity(goToGuide)
        }
    }

    // A method that checks and asks the user for permission to access the camera.
    private fun askCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST
            )
        } else {
            openCamera()
        }
    }

    // Checks to see if permission was granted or not. If the permission is not granted, the user
    // cannot take photos and place in a profile picture for themselves.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(
                    this,
                    "Camera permission is required to input photo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Opens the camera application.
    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, CAMERA_REQUEST)
        Toast.makeText(this, "Camera Open Request", Toast.LENGTH_SHORT).show()
    }

    // Takes the picture that the user takes and displays it in the ImageView. Also converts
    // the image into a byte array to store in shared preferences.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST) {
            val profilePic = findViewById<ImageView>(R.id.picture)
            val image = data?.getExtras()?.get("data")
            profilePic.setImageBitmap(image as Bitmap?)
            val baos = ByteArrayOutputStream()
            image?.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val byte = baos.toByteArray()

            val encoded = Base64.encodeToString(byte, Base64.DEFAULT)

            val sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putString(getString(R.string.image), encoded)
                apply()
            }
        }
    }

    // Saves data to shared preferences
    private fun saveData() {
        val description = findViewById<EditText>(R.id.description).text.toString()
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString(getString(R.string.bio), description)
            apply()
        }

    }
}