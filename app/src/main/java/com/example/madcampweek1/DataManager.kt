package com.example.madcampweek1


// mocking dateset class
class DataManager private constructor(){
    companion object {
        val instance: DataManager by lazy { DataManager() }

    }

    private val absentStudentList = listOf("김민지", "강해린")
    private val presentStudentList = listOf("다니엘", "하니", "혜인")
    private val tabTextList = listOf("프로필", "출석 체크", "월별 출석 현황")
    private val subjectList = listOf("몰입캠프: 프로그래밍과 스타트업", "전산기조직", "프로그래밍의이해")
    private val monthAttendanceList = listOf<Triple<String, Int, Int>>( // increasing order
        Triple("2023-12-01", 10, 1),
        Triple("2023-12-29", 11, 10),
        Triple("2024-01-02", 12, 2),
        Triple("2024-01-05", 10, 6),
        Triple("2024-01-07", 9, 9),
        Triple("2024-01-10", 10, 123),
        Triple("2024-01-11", 10, 6),
        Triple("2024-01-12", 10, 6),
        Triple("2024-01-13", 15, 2),
        Triple("2024-01-14", 14, 8),
        Triple("2024-01-15", 100, 0),
        Triple("2024-01-16", 1, 6),
        Triple("2024-01-20", 0, 0),
        Triple("2024-01-25", 2, 1),
        Triple("2024-02-05", 999, 999),
        Triple("2024-02-15", 100, 6),
        )
    private val attedanceStatus = listOf<Pair<Int, Int>>(
        Pair(12, 15),
        Pair(1, 26),
        Pair(11, 14),
        Pair(12, 15),
        Pair(2, 25),
        Pair(3, 24),
        Pair(0, 27),
        Pair(1, 26),
        Pair(2, 25),
        Pair(27, 0),
        Pair(2, 25),
        Pair(0, 27),
        Pair(1, 26),
        Pair(12, 15),
        Pair(1, 26),
        Pair(0, 27),
        Pair(4, 23),
        Pair(0, 27),
        Pair(12, 15),
        Pair(12, 15)
    )

    //val myProfile =
    fun getAbsentStudentList() : List<String>{ // using at CalendarDialogFragment
        return absentStudentList
    }

    fun getPresentStudentList() : List<String>{ // using at CalendarDialogFragment
        return presentStudentList
    }

    fun getTabTextList() : List<String>{ // using at mainActivity
        return tabTextList
    }

    fun getSubjectList() : List<String> { // using at mainActivity
        return subjectList
    }

    fun getMonthAttendanceList() : List<Triple<String, Int, Int>> { // using at CalendarAdapter
        return monthAttendanceList
    }

    fun getAttedanceStatus() : List<Pair<Int, Int>> {  // using at ProfileAdapter
        return attedanceStatus
    }



}