package com.example.harrypotter_app.viewHolder

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter_app.data.Dao.CharacterDAO

class CharacterViewHolder(view: View, private val itemDao: CharacterDAO): RecyclerView.ViewHolder(view) {

    class InventoryViewModelFactory(private val itemDao: CharacterDAO) : ViewModelProvider.Factory {

    }
}