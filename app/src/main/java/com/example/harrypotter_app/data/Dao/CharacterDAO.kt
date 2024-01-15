package com.example.harrypotter_app.data.Dao

import android.telecom.Call
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harrypotter_app.data.Entity.CharacterEntity
import com.example.harrypotter_app.di.model.modelData.CharacterItem

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg character: CharacterEntity)
}