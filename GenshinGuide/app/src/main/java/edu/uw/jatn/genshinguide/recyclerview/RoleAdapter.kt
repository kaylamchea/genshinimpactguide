/** Kayla Chea: I wrote this Adapter and its methods. * */

package edu.uw.jatn.genshinguide.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uw.jatn.genshinguide.*

class RoleAdapter :
    RecyclerView.Adapter<RoleViewHolder>() {
    private var roles = mutableListOf<Role>()

    // Updates role list and notifies RecyclerView.
    fun setRoleList(charRoles: List<Role>) {
        this.roles = charRoles.toMutableList()
        notifyDataSetChanged()
    }

    // Create views.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RoleViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.role_row_item, viewGroup, false)

        return RoleViewHolder(view)
    }

    // Replaces contents of the views.
    override fun onBindViewHolder(viewHolder: RoleViewHolder, position: Int) {
        viewHolder.charRole.text = roles[position].name

        val weaponAdapter = WeaponAdapter(roles[position].weapons)
        viewHolder.weaponRV.adapter = weaponAdapter

        val artifactAdapter = ArtifactAdapter(roles[position].artifacts)
        viewHolder.artifactRV.adapter = artifactAdapter

        val mainStatAdapter = MainStatAdapter(roles[position].mainStats)
        viewHolder.mainStatRV.adapter = mainStatAdapter

        val subStatAdapter = SubStatAdapter(roles[position].subStats)
        viewHolder.subStatRV.adapter = subStatAdapter
    }

    // Get size of role data set.
    override fun getItemCount(): Int {
        return roles.size
    }
}

// Provide reference to views.
class RoleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val charRole: TextView = view.findViewById(R.id.role)
    val weaponRV: RecyclerView = view.findViewById(R.id.weapon_recyclerview)
    val artifactRV: RecyclerView = view.findViewById(R.id.artifact_recyclerview)
    val mainStatRV: RecyclerView = view.findViewById(R.id.main_stat_recyclerview)
    val subStatRV: RecyclerView = view.findViewById(R.id.sub_stat_recyclerview)
}
