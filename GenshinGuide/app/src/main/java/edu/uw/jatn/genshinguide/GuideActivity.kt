/** Kayla Chea: I wrote this activity (all methods) and built support for landscape mode in this Activity. **/

package edu.uw.jatn.genshinguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.jatn.genshinguide.databinding.ActivityGuideBinding
import android.content.Intent

class GuideActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            val goToProfile = Intent(this, Profile::class.java)
            startActivity(goToProfile)
        }
    }
}