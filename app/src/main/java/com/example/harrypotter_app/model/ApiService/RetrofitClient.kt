package com.example.harrypotter_app.model.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hp-api.onrender.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val ApiService = retrofit.create(ApiService::class.java)
}