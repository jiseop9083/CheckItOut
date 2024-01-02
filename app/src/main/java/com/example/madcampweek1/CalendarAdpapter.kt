package com.example.madcampweek1

// calendar item을 adapt함
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// 높이를 구하는데 필요한 LinearLayout과 FurangCalender를 사용할 때 필요한 date를 받는다.
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val currentDate: Date, val selectedDate: Date) :
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()

    // FurangCalendar을 이용하여 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(selectedDate)
    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int) {
            Log.d("tag", "hello ${view} ${position}")
        }
    }


    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // list_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder?.bind(dataList[position], position, context)
        holder.itemView.setOnClickListener {
            // TODO: call dialog_create_fuction
            val yearMonthString: String = SimpleDateFormat("yyyyMM",
                Locale.KOREA

            ).format(selectedDate.time)
            var dateString: String = holder.itemCalendarDateText.text.toString()
            val inputNumber = dateString.toInt()
            val dayString = if (inputNumber < 10) {
                "0$inputNumber"
            } else {
                dateString
            }
            val calendarDialog = CalendarDialogFragment.newInstance(yearMonthString + dayString)
            calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")
        }
        holder?.itemCalendarDateText?.setOnClickListener {
            // TODO: call dialog_create_fuction
            val yearMonthString: String = SimpleDateFormat("yyyyMM",
                Locale.KOREA

            ).format(selectedDate.time)

            var dateString: String = holder.itemCalendarDateText.text.toString()
            val inputNumber = dateString.toInt()
            val dayString = if (inputNumber < 10) {
                "0$inputNumber"
            } else {
                dateString
            }
            val calendarDialog = CalendarDialogFragment.newInstance(yearMonthString + dayString)
            calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")
        }

        holder?.itemCalendarAbsenceBtn?.setOnClickListener {
            // TODO: call dialog_create_fuction
            val yearMonthString: String = SimpleDateFormat("yyyyMM",
                Locale.KOREA

            ).format(selectedDate.time)

            var dateString: String = holder.itemCalendarDateText.text.toString()
            val inputNumber = dateString.toInt()
            val dayString = if (inputNumber < 10) {
                "0$inputNumber"
            } else {
                dateString
            }
            val calendarDialog = CalendarDialogFragment.newInstance(yearMonthString + dayString)
            calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")
        }
        holder?.itemCalendarAttendBtn?.setOnClickListener {
            // TODO: call dialog_create_fuction
            val yearMonthString: String = SimpleDateFormat("yyyyMM",
                Locale.KOREA
            ).format(selectedDate.time)
            var dateString: String = holder.itemCalendarDateText.text.toString()
            val inputNumber = dateString.toInt()
            val dayString = if (inputNumber < 10) {
                "0$inputNumber"
            } else {
                dateString
            }
            val calendarDialog = CalendarDialogFragment.newInstance(yearMonthString + dayString)
            calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")
        }
    }



    /*
        val calendarDialog = CalendarDialogFragment.newInstance()
        calendarDialog.show((context as AppCompatActivity).supportFragmentManager, "calendar_dialog")
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        // 일반적으로 false로 설정해 자식 view를 동적으로 첨부함
        // true설정하면 자식이 match_parent로 설정되어있으면 height가 충돌해 illegalStateException 발생
        val view =
            LayoutInflater.from(context).inflate(R.layout.calendar_item, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.findViewById(R.id.item_calendar_date_text)
        var itemCalendarAttendBtn: Button = itemView!!.findViewById(R.id.attend_btn)
        var itemCalendarAbsenceBtn: Button = itemView!!.findViewById(R.id.absence_btn)


        fun bind(data: Int, position: Int, context: Context) {
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1

            itemCalendarDateText.setText(data.toString())

            val date = java.util.Calendar.getInstance().run {
                add(java.util.Calendar.MONTH, 0)
                time
            }
            // 포맷 적용
            var currentDay: Int = SimpleDateFormat(
                "dd",
                Locale.KOREA

            ).format(date.time).toInt()

            if (dataList[position] == currentDay && currentDate.time == selectedDate.time) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextAppearance(R.style.LightColorDateTextStyle)
                itemCalendarAttendBtn.visibility = View.INVISIBLE
                itemCalendarAbsenceBtn.visibility = View.INVISIBLE

                itemView.isEnabled = false
                itemCalendarDateText.isEnabled = false

            }

            // TODO: 데이터 가져오기.
            var temp1 = 2
            var temp2 = 3
            itemCalendarAttendBtn.text = temp1.toString()
            itemCalendarAbsenceBtn.text = temp2.toString()

            var classDay = 0

            // 출석, 결석한 사람이 둘 다 0명이면 classDay = 0. => 날짜 검색해서 없으면 classDay = 0으로 하자.
            classDay = if(itemCalendarAttendBtn.text.toString().toInt() == 0 && itemCalendarAbsenceBtn.text.toString().toInt() ==0) 0 else 1

            // 그냥 확인용으로 하나 추가 -> 토요일, 일요일
            if (position % 7 ==0 || position %7 == 6){
                classDay = 0
            }
//            if (this.itemCalendarDateText.text.toString().toInt() % 5 == 0) {
//                classDay = 0
//            }

            if (classDay == 0){
                itemCalendarAttendBtn.visibility = View.INVISIBLE
                itemCalendarAbsenceBtn.visibility = View.INVISIBLE
            }
        }

    }
}