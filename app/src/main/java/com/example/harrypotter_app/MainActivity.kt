package com.example.harrypotter_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter_app.adapter.CharacterAdapter
import com.example.harrypotter_app.databinding.ActivityMainBinding
import com.example.harrypotter_app.model.ApiService.ApiService
import com.example.harrypotter_app.model.modelData.CharacterItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var rvMain: RecyclerView
    lateinit var MyAdapter: CharacterAdapter
    var BASE_URL = "https://hp-api.onrender.com/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rv_character)

        rvMain.layoutManager = LinearLayoutManager(this)

        getAllData()

       /* MyAdapter.onItemClick = {
            val intent= Intent(this, DetailsCharacter::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }*/
    }

    // Creating  initRecyclerView

    private fun getAllData() {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call: Call<List<CharacterItem>> = apiService.getCharacters()

        call.enqueue(object: Callback<List<CharacterItem>> {
            override fun onResponse(
                call: Call<List<CharacterItem>>,
                response: Response<List<CharacterItem>>
            ) {
                var data = response.body()!!
                MyAdapter = CharacterAdapter(baseContext, data)

                rvMain.adapter = MyAdapter


                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<CharacterItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}

