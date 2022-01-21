/** Kayla Chea: I wrote this fragment (all methods) and built support for landscape mode in this fragment. **/

package edu.uw.jatn.genshinguide

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import edu.uw.jatn.genshinguide.databinding.FragmentGuideBinding
import edu.uw.jatn.genshinguide.recyclerview.CharAdapter
import edu.uw.jatn.genshinguide.retrofit.GenshinApiService
import edu.uw.jatn.genshinguide.retrofit.MainRepository
import edu.uw.jatn.genshinguide.viewmodel.GuideViewModel
import edu.uw.jatn.genshinguide.viewmodel.GuideViewModelFactory
import java.util.*

class GuideFragment : Fragment() {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuideViewModel
    private val retrofitService = GenshinApiService.getInstance()
    private val adapter = CharAdapter(::onProfileClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(
                this,
                GuideViewModelFactory(MainRepository(retrofitService))
            )[GuideViewModel::class.java]

        binding.recyclerview.adapter = adapter

        // Observes changes to list of characters and updates RecyclerView.
        viewModel.charList.observe(viewLifecycleOwner, {
            adapter.setCharList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
        })

        // Grabs all Genshin Impact characters.
        viewModel.getAllCharacters()

        // Grabs character based on search input.
        binding.searchButton.setOnClickListener {
            val input = view.findViewById<EditText>(R.id.char_input).text.toString()
                .lowercase(Locale.getDefault()).replace(' ', '-')
            if (CHAR_NAME.contains(input)) {
                viewModel.searchChar(input)
            } else {
                viewModel.getAllCharacters()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Passes instructions on what to do when a character is clicked.
    private fun onProfileClicked(clickedCharacter: Character) {
        val directions =
            GuideFragmentDirections.actionGuideFragmentToCharacterFragment(clickedCharacter)
        findNavController().navigate(directions)
    }
}