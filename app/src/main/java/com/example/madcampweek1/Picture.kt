package com.example.madcampweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcampweek1.databinding.FragmentPictureBinding
import android.widget.GridView
import android.widget.Toast


class Picture : Fragment() {
    private lateinit var binding: FragmentPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(inflater, container, false)

        val flowerName = arrayOf("Rose", "Lily", "Harry", "wa", "jollyu", "abc")
        val flowerImages = intArrayOf(R.drawable.picture1, R.drawable.picture2, R.drawable.picture3, R.drawable.picture4, R.drawable.picture5, R.drawable.picture6)

        val gridAdapter = GridAdapter(requireActivity(), flowerName, flowerImages);
        binding.gridView.adapter = gridAdapter


        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireActivity(), "You Clicked on ${flowerName[position]}", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}
