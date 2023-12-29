package com.example.madcampweek1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcampweek1.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    // lateinit는 var
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val tabTextList = listOf("Profile", "Picture", "Calendar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPagerContainer.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewPagerContainer) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()


//        val json = assets.open("studentInfo.json").reader().readText()
//        val data = JSONObject(json).getJSONObject("studentList")
//
//        val listStore = data.getJSONArray("store_list")



        binding.viewPagerContainer.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPos = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })
    }

}