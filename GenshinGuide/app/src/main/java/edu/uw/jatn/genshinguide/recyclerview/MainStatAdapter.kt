/** Kayla Chea: I wrote this Adapter and its methods. **/

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.jatn.genshinguide.MainStat
import edu.uw.jatn.genshinguide.R

open class MainStatAdapter(mainStats: List<MainStat>) :
    RecyclerView.Adapter<MainStatViewHolder>() {

    private var mainStatList: List<MainStat> = ArrayList()

    // Updates main stat list.
    init {
        this.mainStatList = mainStats
    }

    // Creates views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainStatViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_item, viewGroup, false)

        return MainStatViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: MainStatViewHolder, position: Int) {
        viewHolder.artifactType.text = mainStatList[position].artifactType
        viewHolder.artifact.text = mainStatList[position].type
    }

    // Returns size of main stat dataset.
    override fun getItemCount(): Int {
        return mainStatList.size
    }
}

// Provides reference to views.
class MainStatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val artifactType: TextView = view.findViewById(R.id.left_cell)
    val artifact: TextView = view.findViewById(R.id.right_cell)
}
