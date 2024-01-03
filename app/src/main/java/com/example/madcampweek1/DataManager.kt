package com.example.madcampweek1

// mocking dateset class
class DataManager private constructor(){
    companion object {
        val instance: DataManager by lazy { DataManager() }
    }

    private val tabTextList = listOf("프로필", "출석 체크", "월별 출석 현황")
    private val subjectList = listOf("몰입캠프: 프로그래밍과 스타트업", "전산기조직", "프로그래밍의이해")
    private val monthAttendanceList = listOf<Triple<String, Int, Int>>( // increasing order
        Triple("2023-11-21", 20, 0), // date, present, absent
        Triple("2023-11-23", 17, 3),
        Triple("2023-11-28", 15, 5),
        Triple("2023-11-30", 20, 0),
        Triple("2023-12-05", 0, 20),
        Triple("2023-12-07", 0, 20),
        Triple("2023-12-12", 15, 5),
        Triple("2023-12-14", 16, 4),
        Triple("2023-12-19", 20, 0),
        Triple("2023-12-21", 20, 0),
        Triple("2023-12-26", 0, 20),
        Triple("2023-12-28", 17, 3),
        Triple("2024-01-02", 14, 6)

        )
    private val attedanceStatus = listOf<Pair<Int, Int>>(
        Pair(3, 10),
        Pair(1, 12),
        Pair(0, 13),
        Pair(2, 11),
        Pair(2, 11),
        Pair(0, 13),
        Pair(0, 13),
        Pair(1, 12),
        Pair(4, 9),
        Pair(13, 0),
        Pair(2, 11),
        Pair(0, 13),
        Pair(1, 12),
        Pair(0, 13),
        Pair(0, 13),
        Pair(0, 13),
        Pair(0, 13),
        Pair(0, 13),
        Pair(2, 11),
        Pair(2, 11)
    )


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