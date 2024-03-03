package com.example.noaily.NotesDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class notesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "content")
    var content:String
)
{
    @Ignore
    constructor(title: String, content: String) : this(0, title, content)



}