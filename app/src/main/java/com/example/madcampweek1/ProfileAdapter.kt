package com.example.madcampweek1

import android.content.Context
import android.content.Intent
import android.graphics.Color

import android.net.Uri
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcampweek1.databinding.ProfileItemsBinding

// profile fragment에서 동작하는 adapter

class ProfileAdapter(val context: Context, private var dataSet : ArrayList<ProfileDTO>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    private lateinit var binding: ProfileItemsBinding




    class ViewHolder(private val binding:  ProfileItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val name : TextView = binding.name
        val phoneNumber : TextView = binding.phoneNumber
        val studentID : TextView = binding.studentID
        val messageView : ImageView = binding.messageView
        val callView : ImageView = binding.callView
        val attendanceRate : TextView = binding.attendanceRate
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ProfileItemsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var profile : ProfileDTO = dataSet[position]
        viewHolder.name.text = profile.name
        viewHolder.phoneNumber.text = profile.phoneNumber
        viewHolder.studentID.text = profile.studentID.toString()
        viewHolder.messageView.findViewById<ImageView>(R.id.messageView).setOnClickListener {
            val smsNumber = "smsto:" + profile.phoneNumber // 여기에 전화번호를 넣어주세요
            val msgIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(smsNumber))

            //Intent 시작
            context.startActivity(msgIntent)
        }
        viewHolder.callView.findViewById<ImageView>(R.id.callView).setOnClickListener {
            val phoneNumber = "tel:" + profile.phoneNumber // 여기에 전화번호를 넣어주세요
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))

            //Intent 시작
            context.startActivity(dialIntent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }



}