package com.example.madcampweek1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarPageFragment(index: Int) : Fragment() {

    lateinit var mContext: Context
    lateinit var mActivity: MainActivity

    var pageIndex = index

    lateinit var calendar_year_month_text: TextView
    lateinit var calendar_layout: LinearLayout
    lateinit var calendar_view: RecyclerView
    lateinit var calendarAdapter: CalendarAdapter
    lateinit var up_btn : Button
    lateinit var down_btn : Button


    companion object {
        var instance: CalendarPageFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
            mActivity = activity as MainActivity
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar_page, container, false)

        initView(view)
        return view
    }



    private fun initView(view: View) {
        pageIndex -= (Int.MAX_VALUE / 2)

        calendar_year_month_text = view.findViewById(R.id.calendar_year_month_text)
        calendar_layout =  view.findViewById(R.id.calendar_layout)
        calendar_view = view.findViewById(R.id.calendar_view)
        down_btn = view.findViewById(R.id.down_btn)
        up_btn = view.findViewById(R.id.up_btn)

        // main에서 마음대로 viewPager를 가저오는게 맞을까?(의존성 문제)
        val calendarViewPager: ViewPager2 = mActivity.findViewById(R.id.calendarViewPager)
        down_btn.setOnClickListener {
            val currentItem = calendarViewPager.currentItem
            calendarViewPager.setCurrentItem(currentItem + 1, false)
        }

        up_btn.setOnClickListener {
            val currentItem = calendarViewPager.currentItem
            calendarViewPager.setCurrentItem(currentItem - 1, false)
        }

        // TODO: code refacting : tab3 fragment이름 바꾸기(java의 Calender랑 겹침
        var currentDate : Date = java.util.Calendar.getInstance().run {
            add(java.util.Calendar.MONTH, 0)
            time
        }
        // 포맷 적용
        var selectedDate : Date = java.util.Calendar.getInstance().run {
            add(java.util.Calendar.MONTH, pageIndex)
            time
        }
        calendar_year_month_text.setText(SimpleDateFormat(
            "yyyy년MM월",
            Locale.KOREA
        ).format(selectedDate.time))
        calendarAdapter = CalendarAdapter(mContext, calendar_layout, currentDate, selectedDate)
        calendar_view.adapter = calendarAdapter
        calendar_view.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendar_view.setHasFixedSize(true)
    }

}