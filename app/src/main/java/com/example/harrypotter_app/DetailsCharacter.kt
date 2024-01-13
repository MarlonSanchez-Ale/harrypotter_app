package com.example.harrypotter_app

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.harrypotter_app.model.modelData.CharacterItem
import org.w3c.dom.Text

class DetailsCharacter : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)

        var con: Context
        val txtactor: TextView = findViewById(R.id.txtActor)
        val imageChar: ImageView = findViewById(R.id.imageCharacterDetails)
        val txtNameDet: TextView = findViewById(R.id.txtNameDetails)
        val txtSpecieDet: TextView = findViewById(R.id.txtSpeciesDetails)
        val txtGenderDet: TextView = findViewById(R.id.txtGenderDetails)
        val txtHouse: TextView = findViewById(R.id.txtHouse)
        val txtBirthday: TextView = findViewById(R.id.txtBirthday)
        val txtAge: TextView = findViewById(R.id.txtAge)
        val txtWood: TextView = findViewById(R.id.txtWood)
        val txtCore: TextView = findViewById(R.id.txtCore)
        val txtLength: TextView = findViewById(R.id.txtLength)

        val bundle: Bundle?= intent.extras
        val img = bundle!!.getString("image")
        val actor = bundle!!.getString("actor")
        val name = bundle!!.getString("name")
        val gender = bundle!!.getString("gender")
        val species = bundle!!.getString("species")
        val house = bundle!!.getString("house")
        val dateOfBirth = bundle!!.getString("dateOfBirth")
        val yearOfBirth = bundle!!.getInt("yearOfBirth")
        val wood = bundle!!.getString("wood")
        val core = bundle!!.getString("core")
        val length = bundle!!.getInt("length")
       // if (img?.length ?:  0) {
           Glide.with(this).load(img).into(imageChar)
        //imageChar.setImageResource(img)
        //} else Glide.with(this).load(R.drawable.avatar_potter).into(imageChar)

        txtactor.text  = actor
        txtNameDet.text = name
        txtSpecieDet.text = species
        txtGenderDet.text = gender
        txtHouse.text = house
        txtBirthday.text = dateOfBirth
        txtAge.text = yearOfBirth.toString()
        txtWood.text = wood
        txtCore.text = core
        txtLength.text = length.toString()

    }
}