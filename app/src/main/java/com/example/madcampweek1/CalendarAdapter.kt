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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// 높이를 구하는데 필요한 LinearLayout과 FurangCalender를 사용할 때 필요한 date를 받는다.
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val currentDate: Date, val selectedDate: Date) :
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    var attendanceList: ArrayList<Triple<Int, Int, Int>> = arrayListOf()
    var attendanceCounter : Int = 0
    var isCurrentMonth : Boolean = false


    // FurangCalendar을 이용하여 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(selectedDate)
    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
        attendanceCounter = 0
        isCurrentMonth = false
        initAttendanceList()
    }



    fun initAttendanceList() {
        val dataManager = DataManager.instance
        val monthAttendanceList = dataManager.getMonthAttendanceList()
        val calendar = java.util.Calendar.getInstance()
        calendar.time = selectedDate
        attendanceList.clear()
        val currentYear = calendar.get(java.util.Calendar.YEAR)
        val currentMonth = calendar.get(java.util.Calendar.MONTH) + 1
        var datePointer : Int = 0
        while (datePointer < monthAttendanceList.size){
            val date = monthAttendanceList[datePointer].first.split("-")
            val year = date[0].toInt()
            val month = date[1].toInt()
            val day = date[2].toInt()
            if(currentYear <= year && currentMonth < month){
                break
            }

            if(currentYear == year && currentMonth == month){
                attendanceList.add(Triple(day, monthAttendanceList[datePointer].second, monthAttendanceList[datePointer].third))
            }
            datePointer++
        }
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // list_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h
        var attendanceNum : Pair<Int, Int> = Pair(-1, -1) // exception value
        //val t : Int = attendanceList[attendanceCounter].first
        if(dataList[position] == 1)
            isCurrentMonth = true
        if(isCurrentMonth && attendanceCounter < attendanceList.size  && attendanceList[attendanceCounter].first == dataList[position]){
            attendanceNum = Pair(attendanceList[attendanceCounter].second, attendanceList[attendanceCounter].third)
            attendanceCounter++
        }
        holder?.bind(dataList[position], position, context, attendanceNum)
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


        fun bind(data: Int, position: Int, context: Context, attendance: Pair<Int, Int>) {
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


            itemCalendarAttendBtn.text = attendance.first.toString()
            itemCalendarAbsenceBtn.text = attendance.second.toString()

            var classDay = 0

            // 출석, 결석한 사람이 둘 다 0명이면 classDay = 0. => 날짜 검색해서 없으면 classDay = 0으로 하자.
            classDay = if(itemCalendarAttendBtn.text.toString().toInt() == -1 || itemCalendarAbsenceBtn.text.toString().toInt() == -1) 0 else 1

            if (classDay == 0){
                itemCalendarAttendBtn.visibility = View.INVISIBLE
                itemCalendarAbsenceBtn.visibility = View.INVISIBLE
            }
        }

    }
}