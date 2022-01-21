/** Kayla Chea: I wrote this data class. **/

package edu.uw.jatn.genshinguide

data class Role(
    val name: String,
    val weapons: List<Weapon>,
    val artifacts: List<Artifact>,
    val mainStats: List<MainStat>,
    val subStats: List<SubStat>
)
