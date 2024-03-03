package com.example.noaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.noaily.NotesDB.NotesDBHelper
import com.example.noaily.NotesDB.notesEntity
import com.example.noaily.databinding.ActivityShowNotesBinding

class ShowNotes : AppCompatActivity() {
    lateinit var binding: ActivityShowNotesBinding
    private lateinit var notesDBHelper: NotesDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        notesDBHelper = NotesDBHelper.getDbb(this)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        binding.titleShow.setText(title)
        binding.contentShow.setText(content)

        binding.update.setOnClickListener {
            var updateTitle = binding.titleShow.text.toString()
            var updateContent = binding.contentShow.text.toString()


//            notesDBHelper.notesDao().inertNotes(notesEntity(updateTitle, updateContent))
            Toast.makeText(this, "work pending", Toast.LENGTH_SHORT).show()

            finish()


        }
    }
}