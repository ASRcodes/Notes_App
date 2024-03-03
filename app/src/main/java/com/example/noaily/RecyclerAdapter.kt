package com.example.noaily

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noaily.NotesDB.NotesDBHelper
import com.example.noaily.NotesDB.notesEntity
import com.example.noaily.databinding.RecyclerLayoutBinding

class RecyclerAdapter (
    private val context: Context,
    private val arrNotes: ArrayList<notesEntity>,
    private val noteDbHelper:NotesDBHelper
):RecyclerView.Adapter<RecyclerAdapter.Holder>()
{
    inner class Holder(var binding: RecyclerLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
      return arrNotes.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.titleRecycler.text = arrNotes[position].title
        holder.binding.contentRecyler.text = arrNotes[position].content
        holder.binding.notesItems.setOnClickListener {
            val intent = Intent(context, ShowNotes::class.java)

            intent.putExtra("title",arrNotes[position].title)
            intent.putExtra("content",arrNotes[position].content)
            context.startActivity(intent)
        }
        holder.binding.notesItems.setOnLongClickListener {
            deleteItems(position)
            true
        }
    }

    //    Function to show dialog box  to delete items
    private fun deleteItems(position: Int){

        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete..")
            .setPositiveButton("yes"){
                dialog, which ->
//                val noteIdToDelete = arrNotes[position].id
//                noteDbHelper.notesDao().deleteNotesById(noteIdToDelete)

                noteDbHelper.notesDao().deleteNotesById(arrNotes[position].id)
                noteDbHelper.notesDao().deleteNotes(
                    notesEntity(
//                        arrNotes[position].id,
                        arrNotes[position].title,
                        arrNotes[position].content
                    )
                )
                //                        Calling the method of main Activity show notes after deletion of object..
                (context as NoteScreen).showNotes()
            }
            .setNegativeButton("no"){
                dialog, which ->
                // Do nothing
            }
            .show()
    }
}


