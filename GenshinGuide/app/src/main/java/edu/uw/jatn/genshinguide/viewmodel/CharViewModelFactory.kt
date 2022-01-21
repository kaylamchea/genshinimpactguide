/** Kayla Chea: I wrote this viewmodel factory. **/

package edu.uw.jatn.genshinguide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.uw.jatn.genshinguide.retrofit.MainRepository

class CharViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharViewModel::class.java)) {
            CharViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}