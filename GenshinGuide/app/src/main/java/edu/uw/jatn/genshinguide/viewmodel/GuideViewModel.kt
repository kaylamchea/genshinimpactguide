/** Kayla Chea: I wrote this viewmodel and its methods. **/

package edu.uw.jatn.genshinguide.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uw.jatn.genshinguide.Character
import edu.uw.jatn.genshinguide.retrofit.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuideViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val charList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()

    // Grabs all characters from Genshin Impact.
    fun getAllCharacters() {
        val response = repository.getAllCharacters()

        response.enqueue(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.body() != null) {
                    val body = response.body()
                    charList.postValue(body!!)

                } else {
                    Log.e("MyViewModel", "Nothing Returned")
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Log.e("MyViewModel", "Something went wrong in my network call", t)
            }

        })
    }

    // Searches for user's inputted character.
    fun searchChar(character: String) {
        val response = repository.searchChar(character)

        response.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val body = response.body()
                charList.postValue(listOf(body!!))
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}

