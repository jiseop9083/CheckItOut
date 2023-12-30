package com.example.madcampweek1
// Calendar page를 adapt함 -> 월별 fragment를 가짐
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class CalendarPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val pageCount = Int.MAX_VALUE
    val fragmentPosition = Int.MAX_VALUE / 2

    override fun getItemCount(): Int = pageCount

    override fun createFragment(position: Int): Fragment {
        val calendarPageFragment = CalendarPageFragment(position)
        return calendarPageFragment
    }
}
