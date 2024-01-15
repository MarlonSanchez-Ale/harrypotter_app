package com.example.harrypotter_app.data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harrypotter_app.data.Dao.CharacterDAO
import com.example.harrypotter_app.data.Entity.CharacterEntity
import com.example.harrypotter_app.data.RoomConstants

@Database(entities = [CharacterEntity::class], version = 1)
abstract  class AppDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    RoomConstants.CHARACTER_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}