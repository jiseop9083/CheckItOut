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


class AttendanceAdapter(val context: Context, private val items: List<AttendanceAdapter.DialogItem>) : RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

//    lateinit var recyclerView: RecyclerView

    data class DialogItem(var type: Int, val data: String)
    val TYPE_ATTEND = 0
    val TYPE_ABSENT = 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val calenderPersonView: View = itemView.findViewById(R.id.calender_person_view)
        val textView: TextView = itemView.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType==TYPE_ATTEND || viewType==TYPE_ABSENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_person_item, parent, false)
            ViewHolder(view)
            } else {
                throw IllegalArgumentException("Invalid view type: $viewType")
            }
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_person_item, parent, false)
//        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].data
        holder.itemView.layoutParams.width = (ScreenUtils.getScreenSize(context).x * 0.5).toInt()

//        val viewHolder = holder as ViewHolder
//        viewHolder.bind(item.data)
//
//        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
//        return if (position % 2 == 0) TYPE_ATTEND else TYPE_ABSENT
        return items[position].type
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {

        // 아이템 위치 변경
        Collections.swap(items, fromPosition, toPosition)

        // 아이템 타입 변경 (예시: 1열과 2열을 번갈아가면서 변경)
        items[fromPosition].type = if (toPosition % 2 == 0) TYPE_ATTEND else TYPE_ABSENT

        // RecyclerView에 위치 및 타입 변경을 알림
        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)

        return true
    }

}