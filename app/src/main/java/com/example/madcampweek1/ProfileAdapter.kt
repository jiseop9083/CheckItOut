package com.example.madcampweek1

import android.content.Context
import android.content.Intent


import android.net.Uri
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
    private lateinit var attend : List<Pair<Int, Int>>




    class ViewHolder(private val binding:  ProfileItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val name : TextView = binding.name
        val phoneNumber : TextView = binding.phoneNumber
        val studentID : TextView = binding.studentID
        val messageView : ImageView = binding.messageView
        val callView : ImageView = binding.callView
        val attendanceRate : TextView = binding.attendanceRate
        val presentNumber : TextView = binding.presentNumber
        val absentNumber : TextView = binding.absentNumber


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ProfileItemsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        val dataManager = DataManager.instance
        attend = dataManager.getAttedanceStatus()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var profile : ProfileDTO = dataSet[position]
        viewHolder.presentNumber.text = attend[position].second.toString()
        viewHolder.absentNumber.text =attend[position].first.toString()
        val rate : Float = ((attend[position].second.toFloat() ) / ((attend[position].second + attend[position].first).toFloat()) * 100.0f)
        if(rate == 100.0f) viewHolder.attendanceRate.text = "100%"
        else viewHolder.attendanceRate.text = String.format("%.1f", rate) + "%"

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