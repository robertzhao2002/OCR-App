package com.example.ocr

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter (private val items: List<OCRItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val image_preview = itemView.findViewById<ImageView>(R.id.preview)
        val title = itemView.findViewById<TextView>(R.id.title)
        val playbar = itemView.findViewById<SeekBar>(R.id.playbar)
        val play_button = itemView.findViewById<ImageView>(R.id.play)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemview = inflater.inflate(R.layout.historyrow, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(viewHolder: HistoryAdapter.ViewHolder, position: Int) {
        val item: OCRItem = items[position]
        val image = viewHolder.image_preview
        val title = viewHolder.title
        val playprogressvar = viewHolder.playbar
        val toggleplaypause = viewHolder.play_button
        title.text = item.full_text
        if(item.full_text.length > 30) {
            title.text = item.full_text.substring(0,30) + "..."
        }
        viewHolder.itemView.setOnClickListener {
            val context = viewHolder.itemView.context
            val intent = Intent(context, CurrentActivity::class.java).apply {
                putExtra("item", item)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

