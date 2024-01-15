package com.example.harrypotter_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.harrypotter_app.adapter.CharacterAdapter
import com.example.harrypotter_app.data.Dao.CharacterDAO
import com.example.harrypotter_app.data.DataBase.AppDataBase
import com.example.harrypotter_app.data.Entity.CharacterEntity
import com.example.harrypotter_app.data.RoomConstants
import com.example.harrypotter_app.databinding.ActivityMainBinding
import com.example.harrypotter_app.di.model.ApiService.ApiService
import com.example.harrypotter_app.di.model.modelData.CharacterItem
import com.example.harrypotter_app.di.model.modelData.Wand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var rvMain: RecyclerView
    lateinit var MyAdapter: CharacterAdapter
    private lateinit var characterDao: CharacterDAO
    val database by lazy { Room.databaseBuilder(this, AppDataBase::class.java, RoomConstants.CHARACTER_DATABASE).build() }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rv_character)

        rvMain.layoutManager = LinearLayoutManager(this)


        // Inicializa characterDao
        val database = Room.databaseBuilder(this, AppDataBase::class.java, RoomConstants.CHARACTER_DATABASE).build()
        Log.d("Database", "Database built successfully")
        characterDao = database.characterDao()
        Log.d("Database", "CharacterDao initialized successfully")


            getAllData()

    }

    private fun getAllData() {
        // Cargar desde la base de datos local
        lifecycleScope.launch {
            val localData = withContext(Dispatchers.IO) {
                characterDao.getAllCharacters()
            }

            // Si hay datos locales, mostrarlos
            if (localData.isNotEmpty()) {
                val convertedData = localData.map { characterEntity ->
                    CharacterItem(
                        characterEntity.actor,
                        characterEntity.dateOfBirth,
                        characterEntity.gender,
                        characterEntity.house,
                        characterEntity.id,
                        characterEntity.image,
                        characterEntity.name,
                        characterEntity.species,
                        Wand(characterEntity.wood, characterEntity.core, characterEntity.length), // Puedes ajustar esto según tu lógica
                        false, // Puedes ajustar esto según tu lógica
                        characterEntity.yearOfBirth
                    )
                }
                showData(convertedData)
            }

            // Realizar la llamada a la API solo si no hay datos locales o si se desea actualizar
            fetchDataFromApi()
        }
    }
    private fun fetchDataFromApi() {
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
                if (response.isSuccessful) {
                    var data = response.body()!!

                    // Guardando datos en la base de datos local
                    lifecycleScope.launch {
                       withContext(Dispatchers.IO){
                           for (characterItem in data) {
                               val dateOfBirth = characterItem.dateOfBirth ?: "N/A"
                               val wood = characterItem.wand.wood ?: "N/A"
                               val core = characterItem.wand.core ?: "N/A"
                               val length = characterItem.wand.length?.toString() ?: "N/A"

                               val characterEntity = CharacterEntity(
                                   characterItem.id ?: "N/A",
                                   characterItem.actor ?: "N/A",
                                   characterItem.name ?: "N/A",
                                   characterItem.image ?: "N/A",
                                   characterItem.gender ?: "N/A",
                                   characterItem.species ?: "N/A",
                                   characterItem.house ?: "N/A",
                                   wood,
                                   core,
                                   length,
                                   dateOfBirth,
                                   characterItem.yearOfBirth ?: 0
                               )
                               characterDao.insertAll(characterEntity)
                           }
                       }
                    }

                    // Mostrar datos desde la API
                    showData(data)
                }
            }

            override fun onFailure(call: Call<List<CharacterItem>>, t: Throwable) {
                Log.e("API Call", "Error: ${t.message}")
            }
        })
    }

    private fun showData(data: List<CharacterItem>) {
        MyAdapter = CharacterAdapter(baseContext, data)
        rvMain.adapter = MyAdapter

        MyAdapter.onItemClickListener(object : CharacterAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailsCharacter::class.java)
                intent.putExtra("image", data[position].image)
                // ... (otras propiedades)
                startActivity(intent)
            }
        })

        MyAdapter.onItemClickListener(object : CharacterAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailsCharacter::class.java)
                intent.putExtra("image", data[position].image)
                intent.putExtra("actor", data[position].actor)
                intent.putExtra("name", data[position].name)
                intent.putExtra("gender", data[position].gender)
                intent.putExtra("species", data[position].species)
                intent.putExtra("house", data[position].house)
                intent.putExtra("dateOfBirth", data[position].dateOfBirth)
                intent.putExtra("yearOfBirth", data[position].yearOfBirth)
                intent.putExtra("wood", data[position].wand.wood)
                intent.putExtra("core", data[position].wand.core)
                intent.putExtra("length", data[position].wand.length)
                startActivity(intent)
            }
        })


    }
}
    // Creating  initRecyclerView

    /*private fun getAllData() {
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

                if (response.isSuccessful) {
                    var data = response.body()!!

                    //Guardando datos en base de datos local
                    GlobalScope.launch {
                        for (characterItem in data){
                            val dateOfBirth = characterItem.dateOfBirth ?: "N/A"
                            val characterEntity = CharacterEntity(
                                characterItem.id,
                                characterItem.actor,
                                characterItem.name,
                                characterItem.image,
                                characterItem.gender,
                                characterItem.species,
                                characterItem.house,
                                characterItem.wand.wood,
                                characterItem.wand.core,
                                characterItem.wand.length.toString(),
                                dateOfBirth,
                                characterItem.yearOfBirth
                            )
                            characterDao.insertAll(characterEntity)
                        }
                    }

                    MyAdapter = CharacterAdapter(baseContext, data)

                    rvMain.adapter = MyAdapter

                    Log.d("data",data.toString())

                    MyAdapter.onItemClickListener(object : CharacterAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MainActivity, DetailsCharacter::class.java)
                            intent.putExtra("image", data[position].image)
                            intent.putExtra("actor", data[position].actor)
                            intent.putExtra("name", data[position].name)
                            intent.putExtra("gender", data[position].gender)
                            intent.putExtra("species", data[position].species)
                            intent.putExtra("house", data[position].house)
                            intent.putExtra("dateOfBirth", data[position].dateOfBirth)
                            intent.putExtra("yearOfBirth", data[position].yearOfBirth)
                            intent.putExtra("wood", data[position].wand.wood)
                            intent.putExtra("core", data[position].wand.core)
                            intent.putExtra("length", data[position].wand.length)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<CharacterItem>>, t: Throwable) {
                Log.e("API Call", "Error: ${t.message}")
            }

        })


    }*/



