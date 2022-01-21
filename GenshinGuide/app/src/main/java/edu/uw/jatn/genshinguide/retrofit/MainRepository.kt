/** Kayla Chea: I wrote this class and its methods. **/

package edu.uw.jatn.genshinguide.retrofit

class MainRepository constructor(private val retrofitService: GenshinApiService) {
    fun getAllCharacters() = retrofitService.getAllCharacters()

    fun searchChar(character: String) = retrofitService.searchChar(character)

    fun getCharDetails(character: String) = retrofitService.getCharDetails(character)
}