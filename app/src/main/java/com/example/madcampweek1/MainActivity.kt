package com.example.madcampweek1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcampweek1.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    // lateinit는 var
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val tabTextList = listOf("Profile", "Search", "Setting")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPagerContainer.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewPagerContainer) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()

        binding.viewPagerContainer.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPos = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if(currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPos == position) {

                    if(currentPos == 0) binding.viewPagerContainer.currentItem = 2
                    else if(currentPos == 2) binding.viewPagerContainer.currentItem = 0
                    else currentPos++
                }
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