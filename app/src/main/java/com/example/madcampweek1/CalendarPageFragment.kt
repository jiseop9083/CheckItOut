package com.example.madcampweek1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarPageFragment(index: Int) : Fragment() {

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context
    lateinit var mActivity: MainActivity

    var pageIndex = index
    lateinit var currentDate: Date

    lateinit var calendar_year_month_text: TextView
    lateinit var calendar_layout: LinearLayout
    lateinit var calendar_view: RecyclerView
    lateinit var calendarAdapter: CalendarAdapter


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

        // TODO: code refacting : tab3 fragment이름 바꾸기(java의 Calender랑 겹침
        val date = java.util.Calendar.getInstance().run {
            add(java.util.Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        // 포맷 적용
        var datetime: String = SimpleDateFormat(
            mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA
        ).format(date.time)
        calendar_year_month_text.setText(datetime)
        calendarAdapter = CalendarAdapter(mContext, calendar_layout, currentDate)
        calendar_view.adapter = calendarAdapter
        calendar_view.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendar_view.setHasFixedSize(true)
    }

}