/** Kayla Chea: I wrote this Adapter and its methods. **/

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uw.jatn.genshinguide.Character
import edu.uw.jatn.genshinguide.R

class CharAdapter(private val onProfileClicked: (Character) -> Unit) :
    RecyclerView.Adapter<CharViewHolder>() {
    private var characters = mutableListOf<Character>()

    // Updates character list and notifies RecyclerView.
    fun setCharList(characters: List<Character>) {
        this.characters = characters.toMutableList()
        notifyDataSetChanged()
    }

    // Creates views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.char_row_item, viewGroup, false)

        return CharViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: CharViewHolder, position: Int) {

        val element: String = if (characters[position].element == "NULL") {
            "???"
        } else {
            characters[position].element
        }

        viewHolder.charName.text = characters[position].name
        viewHolder.charTitle.text = characters[position].title
        viewHolder.charDetail.text = characters[position].rarity.toString() + " star · " + element

        Glide.with(viewHolder.charImage).load(characters[position].icon).into(viewHolder.charImage)

        viewHolder.profileContainer.setOnClickListener {
            onProfileClicked(characters[position])
        }
    }

    // Returns size of the character dataset.
    override fun getItemCount(): Int {
        return characters.size
    }
}

// Provide reference to views.
class CharViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val charName: TextView = view.findViewById(R.id.charName)
    val charTitle: TextView = view.findViewById(R.id.charTitle)
    val charDetail: TextView = view.findViewById(R.id.charDetail)
    val charImage: ImageView = view.findViewById(R.id.charImage)
    val profileContainer: LinearLayout = view.findViewById(R.id.profile_container)
}