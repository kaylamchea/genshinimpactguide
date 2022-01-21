/** Kayla Chea: I wrote this viewmodel factory. **/

package edu.uw.jatn.genshinguide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.uw.jatn.genshinguide.retrofit.MainRepository

class GuideViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GuideViewModel::class.java)) {
            GuideViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}