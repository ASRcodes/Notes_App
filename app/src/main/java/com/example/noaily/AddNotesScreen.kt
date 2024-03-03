package com.example.noaily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noaily.NotesDB.NotesDBHelper
import com.example.noaily.NotesDB.notesEntity
import com.example.noaily.databinding.ActivityAddNotesScreenBinding

class AddNotesScreen : AppCompatActivity() {
    lateinit var binding: ActivityAddNotesScreenBinding
    private lateinit var notesDBHelper:NotesDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesDBHelper = NotesDBHelper.getDbb(this)

        binding.save.setOnClickListener {

            val content = binding.content.text.toString()
            val title = binding.title.text.toString()

            if (content.isEmpty() && title.isEmpty()){
                Toast.makeText(this, "Write something in notes...", Toast.LENGTH_SHORT).show()
            }
            else{
            notesDBHelper.notesDao().inertNotes(notesEntity(title,content))
                Toast.makeText(this, "Notes saved to database", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AddNotesScreen,NoteScreen::class.java)
                intent.putExtra("title",title)
                startActivity(intent)
                finish()
            }
        }

    }
}