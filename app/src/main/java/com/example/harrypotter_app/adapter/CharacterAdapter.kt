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
import de.hdodenhof.circleimageview.CircleImageView

class CharacterAdapter(var con: Context, var character: List<CharacterItem>):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun onItemClickListener (listener: onItemClickListener) {
        mListener = listener
    }

    inner class ViewHolder(v: View, listener: onItemClickListener) : RecyclerView.ViewHolder(v) {
        var img = v.findViewById<CircleImageView>(R.id.imageCharacter)
        var name = v.findViewById<TextView>(R.id.txtName)
        var species = v.findViewById<TextView>(R.id.txtSpecies)
        var gender = v.findViewById<TextView>(R.id.txtGender)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.card_character, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = character[position]
        if (currentItem.image.length > 0) {
            Glide.with(con).load(currentItem.image).into(holder.img)
        } else Glide.with(con).load(R.drawable.avatar_potter).into(holder.img)
        holder.name.text = currentItem.name
        holder.species.text = currentItem.species
        holder.gender.text = currentItem.gender

    }

    override fun getItemCount(): Int = character.size


}