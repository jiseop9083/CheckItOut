package com.example.madcampweek1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.madcampweek1.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    // lateinit는 var
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val dataManager = DataManager.instance
    private val tabTextList = dataManager.getTabTextList()
    private val subjectList = dataManager.getSubjectList()
    private val tabIconList = listOf(R.drawable.profile, R.drawable.check, R.drawable.calendar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this, LoadingActivity::class.java)
        startActivity(intent)

        val i = binding.tabs.tabCount

        binding.viewPagerContainer.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPagerContainer) { tab, pos ->
            tab.text = tabTextList[pos]
            tab.setIcon(tabIconList[pos])
        }.attach()

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
                //binding.subjectText.text =subjectList[currentPos]
                binding.titleText.text = tabTextList[currentPos]
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state

                super.onPageScrollStateChanged(state)
            }
        })

    }

}