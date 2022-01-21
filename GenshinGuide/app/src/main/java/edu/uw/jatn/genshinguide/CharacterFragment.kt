/** Kayla Chea: I wrote this fragment (all methods) and built support for landscape mode in this fragment. **/

package edu.uw.jatn.genshinguide

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import edu.uw.jatn.genshinguide.databinding.FragmentCharacterBinding
import edu.uw.jatn.genshinguide.recyclerview.RoleAdapter
import edu.uw.jatn.genshinguide.retrofit.GenshinApiService
import edu.uw.jatn.genshinguide.retrofit.MainRepository
import edu.uw.jatn.genshinguide.viewmodel.CharViewModel
import edu.uw.jatn.genshinguide.viewmodel.CharViewModelFactory

class CharacterFragment : Fragment() {
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    // Grabs data passed from GuideFragment.
    private val args: CharacterFragmentArgs by navArgs()

    private lateinit var viewModel: CharViewModel
    private var adapter = RoleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(
                this,
                CharViewModelFactory(MainRepository(GenshinApiService.getInstance()))
            )[CharViewModel::class.java]

        binding.charRecyclerView.adapter = adapter

        // Grabs detailed information about clicked character.
        val character = args.clickedChar
        viewModel.getCharDetails(character.id)

        // Updates UI of detailed character page based on passed in character.
        viewModel.character.observe(viewLifecycleOwner, {
            val charName = view.findViewById<TextView>(R.id.detailed_name)
            charName.text = character.name

            val title = view.findViewById<TextView>(R.id.detailed_title)
            title.text = character.title

            val rarity = view.findViewById<TextView>(R.id.detailed_rarity)
            rarity.text = character.rarity.toString() + " star"

            val element = view.findViewById<TextView>(R.id.detailed_element)
            element.text = character.element

            val detailedChar = viewModel.character.value

            val tier = view.findViewById<TextView>(R.id.detailed_rating)
            val weapon = view.findViewById<TextView>(R.id.detailed_weapon)
            val picture = view.findViewById<ImageView>(R.id.detailed_image)
            val recRole = view.findViewById<TextView>(R.id.detailed_role)

            if (detailedChar != null) {
                detailedChar.roles?.let { it1 -> adapter.setRoleList(it1) }
                tier.text = detailedChar.tier
                weapon.text = detailedChar.weapon
                Glide.with(picture).load(detailedChar.squareCard).into(picture)
                val charRole = detailedChar.characterOverview
                recRole.text = charRole?.recommendedRole
            }
        })
    }
}