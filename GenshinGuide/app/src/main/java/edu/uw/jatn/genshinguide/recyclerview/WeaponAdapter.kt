/** Kayla Chea: I wrote this Adapter and its methods. **/

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.jatn.genshinguide.R
import edu.uw.jatn.genshinguide.Weapon
import java.util.*
import kotlin.collections.ArrayList

open class WeaponAdapter(weapons: List<Weapon>) :
    RecyclerView.Adapter<WeaponViewHolder>() {

    private var weaponList: List<Weapon> = ArrayList()

    // Updates weapon list.
    init {
        this.weaponList = weapons
    }

    // Creates views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeaponViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_item, viewGroup, false)

        return WeaponViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: WeaponViewHolder, position: Int) {
        viewHolder.rank.text = weaponList[position].rank.toString()
        viewHolder.weapon.text = weaponList[position].weaponId.replace("-", " ")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    // Returns size of weapon dataset
    override fun getItemCount(): Int {
        return weaponList.size
    }
}

// Provides references to views
class WeaponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rank: TextView = view.findViewById(R.id.left_cell)
    val weapon: TextView = view.findViewById(R.id.right_cell)
}
