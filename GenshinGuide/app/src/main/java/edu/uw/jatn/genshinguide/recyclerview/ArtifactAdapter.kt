/** Kayla Chea: I wrote this Adapter and its methods. **/

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.jatn.genshinguide.Artifact
import edu.uw.jatn.genshinguide.R
import java.util.*
import kotlin.collections.ArrayList

open class ArtifactAdapter(artifacts: List<Artifact>) :
    RecyclerView.Adapter<ArtifactViewHolder>() {

    private var artifactList: List<Artifact> = ArrayList()

    // Updates artifact list.
    init {
        this.artifactList = artifacts
    }

    // Creates views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArtifactViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_item, viewGroup, false)

        return ArtifactViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: ArtifactViewHolder, position: Int) {
        viewHolder.rank.text = artifactList[position].rank.toString()
        viewHolder.artifact.text = artifactList[position].artifactSetId.replace("-", " ")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    // Returns size of artifact dataset.
    override fun getItemCount(): Int {
        return artifactList.size
    }
}

// Provides reference to views.
class ArtifactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rank: TextView = view.findViewById(R.id.left_cell)
    val artifact: TextView = view.findViewById(R.id.right_cell)
}
