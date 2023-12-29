package com.example.madcampweek1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcampweek1.databinding.ProfileItemsBinding


class ProfileAdapter(private var dataSet : ArrayList<ProfileDTO>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    private lateinit var binding: ProfileItemsBinding


    class ViewHolder(private val binding:  ProfileItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val name : TextView = binding.name
        val major : TextView = binding.major
        val phoneNumber : TextView = binding.phoneNumber
        val studentID : TextView = binding.studentID
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ProfileItemsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var profile : ProfileDTO = dataSet[position]

        viewHolder.name.text = profile.name
        viewHolder.major.text = profile.major.toString()
        viewHolder.phoneNumber.text = profile.phoneNumber
        viewHolder.studentID.text = profile.studentID.toString()

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }



}