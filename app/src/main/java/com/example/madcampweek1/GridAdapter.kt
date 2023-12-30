package com.example.madcampweek1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.madcampweek1.databinding.ProfileItemsBinding

class GridAdapter(private val context: Context, private val names: List<String>, private val images: List<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // convertView: 이전에 생성된 뷰 재활용, parent: 부모 뷰 그룹 (이 뷰를 포함하는, layout..)
        val view: View
        val holder: ViewHolder

        if (convertView == null) { // null이면 새 뷰 생성하고 holder에 연결
            view = inflater.inflate(R.layout.grid_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else { // 존재하면 convertView 재사용, holder 가져옴
            view = convertView
            holder = view.tag as ViewHolder // 재활용할 때 해당 뷰에 연결된 viewholder 가져옴. as: 타입 변환
        }

        // 이미지 로드 및 표시
        Glide.with(context)
            .load(images[position]) // 파일 경로에서 로드
            .centerCrop()
            .into(holder.imageView) // 로드한 이미지를 특정 imageView에 표시하도록 지정

        holder.textView.text = names[position]

        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.grid_image)
        val textView: TextView = view.findViewById(R.id.item_name)
    }
}

//
//class GridAdapter(private val context: Context, private val studentNames: Array<String>, private val image: IntArray) : BaseAdapter() {
//
//    private var inflater: LayoutInflater? = null
//
//    override fun getCount(): Int {
//        return studentNames.size
//    }
//
//    override fun getItem(position: Int): Any? {
//        return null
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        if (inflater == null) {
//            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        }
//
//        var convertViewVar = convertView
//        if (convertViewVar == null) {
//            convertViewVar = inflater?.inflate(R.layout.grid_item, null)
//        }
//
//        val imageView = convertViewVar?.findViewById<ImageView>(R.id.grid_image)
//        val textView = convertViewVar?.findViewById<TextView>(R.id.item_name)
//
//        imageView?.setImageResource(image[position])
//        textView?.text = studentNames[position]
//
//        return convertViewVar!!
//    }
//}