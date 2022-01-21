/** Kayla Chea: I wrote this Adapter and its methods. **/

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.jatn.genshinguide.R
import edu.uw.jatn.genshinguide.SubStat

open class SubStatAdapter(SubStats: List<SubStat>) :
    RecyclerView.Adapter<SubStatViewHolder>() {

    private var subStatList: List<SubStat> = ArrayList()

    // Updates sub stat list.
    init {
        this.subStatList = SubStats
    }

    // Creates views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SubStatViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_item, viewGroup, false)

        return SubStatViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: SubStatViewHolder, position: Int) {
        viewHolder.rank.text = subStatList[position].rank.toString()
        viewHolder.artifact.text = subStatList[position].type
    }

    // Returns size of sub stat dataset.
    override fun getItemCount(): Int {
        return subStatList.size
    }
}

// Provides references to view.
class SubStatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rank: TextView = view.findViewById(R.id.left_cell)
    val artifact: TextView = view.findViewById(R.id.right_cell)
}
