package com.example.noaily.NotesDB

import android.icu.text.CaseMap.Title
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface notesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<notesEntity>
    @Insert
    fun inertNotes(notesEntity: notesEntity)
    @Delete
    fun deleteNotes(notesEntity: notesEntity)
    @Update
    fun updateNotes(notesEntity: notesEntity)

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteNotesById(noteId: Int)
}