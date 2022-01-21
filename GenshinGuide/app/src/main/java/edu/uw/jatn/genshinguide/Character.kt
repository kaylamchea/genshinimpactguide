/** Kayla Chea: I wrote this data class. **/

package edu.uw.jatn.genshinguide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: String,
    val name: String,
    val icon: String,
    val title: String,
    val rarity: Int,
    val element: String
) : Parcelable
