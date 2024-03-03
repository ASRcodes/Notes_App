package com.example.noaily

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noaily.NotesDB.NotesDBHelper
import com.example.noaily.databinding.ActivityNoteScreenBinding

class NoteScreen : AppCompatActivity() {
    lateinit var binding: ActivityNoteScreenBinding
    private lateinit var notesDBHelper: NotesDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerNotes.layoutManager = layoutManager

        notesDBHelper = NotesDBHelper.getDbb(this)
        showNotes()


    binding.addNotes.setOnClickListener {
        startActivity(Intent(this@NoteScreen,AddNotesScreen::class.java))
        finish()
    }
        binding.notesfloatbtn.setOnClickListener{
//        to perform the same work what will be done by the addNotes
            binding.addNotes.performClick()
        }


    }

    fun showNotes() {
//        Fetching all the notes
        val notesArr = notesDBHelper.notesDao().getAllNotes()
        if (notesArr.isNotEmpty()){
            binding.recyclerNotes.visibility = View.VISIBLE
            binding.llNotes.visibility = View.GONE

//            Creating Adapter for recyclerView
            val recyclerAdapterNotes = RecyclerAdapter(this,ArrayList(notesArr),notesDBHelper)
//            Setting up the adapter over recyclerView
            binding.recyclerNotes.layoutManager = GridLayoutManager(this,2)
            binding.recyclerNotes.adapter = recyclerAdapterNotes
        }
        else{
            binding.recyclerNotes.visibility = View.GONE
            binding.llNotes.visibility = View.VISIBLE
        }
    }
}