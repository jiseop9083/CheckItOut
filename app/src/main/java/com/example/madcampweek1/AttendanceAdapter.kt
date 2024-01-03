package com.example.madcampweek1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections


class AttendanceAdapter(private val items: List<String>) : RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

    data class DialogItem(var type: Int, val data: String)
    val TYPE_ATTEND = 0
    val TYPE_ABSENT = 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType==TYPE_ATTEND || viewType==TYPE_ABSENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_person_item, parent, false)
            ViewHolder(view)

        } else {
            throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }



}