/** Kayla Chea: I wrote this viewmodel and its methods. **/

package edu.uw.jatn.genshinguide.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uw.jatn.genshinguide.CharDetails
import edu.uw.jatn.genshinguide.retrofit.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val character = MutableLiveData<CharDetails>()

    // Gets clicked character's details.
    fun getCharDetails(charName: String) {
        val response = repository.getCharDetails(charName)

        response.enqueue(object : Callback<CharDetails> {
            override fun onResponse(
                call: Call<CharDetails>,
                response: Response<CharDetails>
            ) {
                if (response.body() != null) {
                    val body = response.body()
                    character.postValue(body!!)
                } else {
                    Log.e("MyViewModel", "Nothing Returned")
                }
            }

            override fun onFailure(call: Call<CharDetails>, t: Throwable) {
                Log.e("MyViewModel", "Something went wrong in my network call", t)
            }
        })
    }
}

