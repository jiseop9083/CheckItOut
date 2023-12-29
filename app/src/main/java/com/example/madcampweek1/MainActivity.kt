package com.example.madcampweek1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcampweek1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import android.R

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTabLayout()
    }

    private fun setTabLayout() {
        // 초기 tab 세팅
        binding.tabLayoutContainer.setBackgroundResource(R.color.black)
        //storeFragmentTablayout
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            // tab이 선택되었을 때
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> binding.tabLayoutContainer.setBackgroundResource(R.color.holo_purple)
                    1 -> binding.tabLayoutContainer.setBackgroundResource(R.color.black)
                    2 -> binding.tabLayoutContainer.setBackgroundResource(R.color.holo_green_light)
                    3 -> binding.tabLayoutContainer.setBackgroundResource(R.color.holo_blue_bright)
                }
            }
            // tab이 선택되지 않았을 때
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            // tab이 다시 선택되었을 때
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}