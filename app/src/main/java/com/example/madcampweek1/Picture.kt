package com.example.madcampweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcampweek1.databinding.FragmentPictureBinding


class Picture : Fragment() {
    private lateinit var binding: FragmentPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }
}
