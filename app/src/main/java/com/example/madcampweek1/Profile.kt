package com.example.madcampweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcampweek1.databinding.FragmentAttendanceBinding


class Profile : Fragment() {
    private lateinit var binding: FragmentAttendanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }
}