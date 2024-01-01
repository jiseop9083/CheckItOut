package com.example.madcampweek1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.madcampweek1.databinding.GridItemBinding
import com.example.madcampweek1.databinding.ProfileItemsBinding

class GridAdapter(private val context: Context, private val names: List<String>, private val images: List<String>) : BaseAdapter() {
    private lateinit var binding: GridItemBinding
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
            
            // TODO: 두 구문 차이 알아보기
            // view의 root view객체를 참조할 수 없으면 해당 레이아웃 내의 뷰를 조작할 수 없다고 함
            //binding = GridItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
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
        val attendBtn = context.resources.getDrawable(R.drawable.attend_btn, null)
        val absenceBtn = context.resources.getDrawable(R.drawable.absence_btn, null)
        holder.imageView.setOnClickListener {
            Toast.makeText(context, "You Clicked on ${names[position]}", Toast.LENGTH_LONG).show()

            // TODO: move it to calendar
            val calendarDialog = CalendarDialogFragment.newInstance()
            calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")


            if(holder.button.text == "출석"){
                holder.button.text = "결석"
                holder.button.background = absenceBtn
            } else{
                holder.button.text = "출석"
                holder.button.background = attendBtn
            }

        }

        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.grid_image)
        val textView: TextView = view.findViewById(R.id.item_name)
        val button: Button = view.findViewById(R.id.attend_btn)
    }
}
