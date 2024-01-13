package com.example.harrypotter_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.harrypotter_app.model.modelData.CharacterItem

class DetailsCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)

        val getData = intent.getParcelableExtra<CharacterItem>("android")

        if (getData != null) {
            val actor: TextView = findViewById(R.id.txtActor)
            val imageChar: ImageView = findViewById(R.id.imageCharacterDetails)
            val nameDet: TextView = findViewById(R.id.txtNameDetails)
            val specieDet: TextView = findViewById(R.id.txtSpecies)
            val genderDet: TextView = findViewById(R.id.txtGenderDetails)
            val house: TextView = findViewById(R.id.txtHouse)
            val birthday: TextView = findViewById(R.id.txtBirthday)
            val age: TextView = findViewById(R.id.txtAge)

            if (getData.image !=  null) {
                Glide.with(this).load(getData.image).into(imageChar)
            } else Glide.with(this).load(R.drawable.avatar_potter).into(imageChar)

            actor.text = getData.actor
            nameDet.text = getData.name
            specieDet.text = getData.species
            genderDet.text = getData.gender
            house.text = getData.house
            birthday.text = getData.dateOfBirth
            age.text = getData.patronus
        }
    }
}