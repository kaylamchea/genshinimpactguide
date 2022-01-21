/** Kayla Chea: I wrote this API interface. **/

package edu.uw.jatn.genshinguide.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import edu.uw.jatn.genshinguide.BASE_URL
import edu.uw.jatn.genshinguide.CharDetails
import edu.uw.jatn.genshinguide.Character
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Creates API interface.
interface GenshinApiService {
    @GET("characters/")
    fun getAllCharacters(): Call<List<Character>>

    @GET("characters/{id}")
    fun searchChar(@Path("id") id: String): Call<Character>

    @GET("characters/{id}/?expand=talents,constellations,roles,overview")
    fun getCharDetails(@Path("id") id: String): Call<CharDetails>

    companion object {
        private var retrofitService: GenshinApiService? = null

        // Creates Retrofit service instance.
        fun getInstance(): GenshinApiService {
            // Creates Moshi object.
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            // Parses JSON from API.
            if (retrofitService == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .baseUrl(BASE_URL)
                    .build()
                retrofitService = retrofit.create(GenshinApiService::class.java)
            }
            return retrofitService!!
        }
    }
}