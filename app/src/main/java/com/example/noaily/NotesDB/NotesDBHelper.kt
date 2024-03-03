package com.example.noaily.NotesDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[notesEntity::class], exportSchema = false, version = 1)
abstract class NotesDBHelper: RoomDatabase() {
    companion object{
        private const val DATABASE_NAME = "notes_DB"
        private var instance: NotesDBHelper? = null

        @Synchronized
        fun getDbb(context: Context): NotesDBHelper{
            if (instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDBHelper::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
    abstract fun notesDao(): notesDao
}