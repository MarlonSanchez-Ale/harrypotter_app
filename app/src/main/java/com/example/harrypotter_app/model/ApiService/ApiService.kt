package com.example.harrypotter_app.model.ApiService


import com.example.harrypotter_app.model.modelData.CharacterItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    fun getCharacters(): Call<List<CharacterItem>>
}