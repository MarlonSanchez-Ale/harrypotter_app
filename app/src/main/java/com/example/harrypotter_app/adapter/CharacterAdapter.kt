package com.example.harrypotter_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter_app.R
import com.example.harrypotter_app.model.modelData.CharacterItem

class CharacterAdapter(var con: Context, var character: List<CharacterItem>):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var img = v.findViewById<ImageView>(R.id.imageCharacter)
        var name = v.findViewById<TextView>(R.id.txtName)
        var species = v.findViewById<TextView>(R.id.txtSpecies)
        var gender = v.findViewById<TextView>(R.id.txtGender)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.card_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(con).load(character[position].image).into(holder.img)
        holder.name.text = character[position].name
        holder.species.text = character[position].species
        holder.gender.text = character[position].gender
    }

    override fun getItemCount(): Int = character.size


}