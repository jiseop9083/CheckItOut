package com.example.madcampweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcampweek1.databinding.FragmentCalendarBinding
import androidx.viewpager2.widget.ViewPager2

class Calendar : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var calendarViewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        calendarViewPager = binding.calendarViewPager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val calendarPagerAdapter
                = CalendarPagerAdapter(requireActivity())
        calendarViewPager.adapter = calendarPagerAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // something wrong!!!
        calendarPagerAdapter.apply {
            calendarViewPager.setCurrentItem(this.fragmentPosition, true)
        }
    }
}