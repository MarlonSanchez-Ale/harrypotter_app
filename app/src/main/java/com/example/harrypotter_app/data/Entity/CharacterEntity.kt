package com.example.harrypotter_app.data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "actor") val actor: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "house") val house: String,
    @ColumnInfo(name = "wood") val wood: String,
    @ColumnInfo(name = "core") val core: String,
    @ColumnInfo(name = "length") val length: String,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth: String,
    @ColumnInfo(name = "yearOfBirth") val yearOfBirth: Int
)