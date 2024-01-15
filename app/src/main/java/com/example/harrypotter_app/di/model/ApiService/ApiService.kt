package com.example.harrypotter_app.di.model.ApiService


import com.example.harrypotter_app.di.model.modelData.CharacterItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    fun getCharacters(): Call<List<CharacterItem>>
}