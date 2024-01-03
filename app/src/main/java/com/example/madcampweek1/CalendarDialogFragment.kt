package com.example.madcampweek1

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject


class CalendarDialogFragment : DialogFragment() {

    private lateinit var attendView: RecyclerView
    private lateinit var absentView: RecyclerView
    private lateinit var attendAdapter: AttendanceAdapter
    private lateinit var absentAdapter: AttendanceAdapter
    lateinit var mContext: Context
    lateinit var mActivity: MainActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
            mActivity = activity as MainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar_dialog, container, false)

        // 전달된 데이터 확인
        val argDate = arguments?.getString("date")
        mActivity
        // For dialog title
        val formatterInput = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 출석부")
        val date = LocalDate.parse(argDate, formatterInput)

        val json = mActivity!!.getAssets().open("attendanceInfo.json").reader().readText()
        var jsonArray = JSONObject(json).getJSONArray("attendanceList")

        var absentArray : ArrayList<String> = ArrayList<String>()
        var presentArray : ArrayList<String> = ArrayList<String>()
        Log.d("ddd", jsonArray.length().toString())
        for (i in 0 until jsonArray.length()) {
            val date = jsonArray.getJSONObject(i).getString("date")
            if (date == argDate) {
                Log.d("ddd", date.toString())
                var presentJsonArray =  jsonArray.getJSONObject(i).getJSONArray("present")
                var absentJsonArray =  jsonArray.getJSONObject(i).getJSONArray("absent")
                Log.d("ddd", "Dd" + presentJsonArray.length().toString())
                for (j in 0 until absentJsonArray.length()) {
                    absentArray.add(absentJsonArray.getString(j))
                }
                for (j in 0 until presentJsonArray.length()) {
                    presentArray.add(presentJsonArray.getString(j))
                }
            }
        }
        Log.d("ddd", presentArray.size.toString())

        // TextView에 데이터 설정
        val textView = view.findViewById<TextView>(R.id.clickedDate)
        textView.text = date.format(formatterOutput)

        attendView = view.findViewById(R.id.attendRecyclerView)
        absentView = view.findViewById(R.id.absentRecyclerView)


        val attendNumView = view.findViewById<TextView>(R.id.attendNumber)
        val absentNumView = view.findViewById<TextView>(R.id.absentNumber)
        attendNumView.text = presentArray.size.toString()
        absentNumView.text = absentArray.size.toString()



        // 어댑터 초기화
        attendAdapter = AttendanceAdapter(presentArray)
        absentAdapter = AttendanceAdapter(absentArray)

        // 리사이클러뷰 설정
        setupRecyclerView(attendView, attendAdapter)
        setupRecyclerView(absentView, absentAdapter)

        val closeButton = view.findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            // 여기에 'X' 버튼 클릭 시 수행할 동작을 추가
            dismiss() // 다이얼로그 닫기
        }


        return view


    }
    private fun setupRecyclerView(recyclerView: RecyclerView, adapter: AttendanceAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
//        adapter.recyclerView = recyclerView
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // 커스텀 다이얼로그 레이아웃을 inflate
        val customDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_calendar_dialog, null)

        // 다이얼로그에 커스텀 레이아웃 설정
        dialog.setContentView(customDialogView)

        // 다이얼로그의 크기를 화면 전체로 설정
        val width = (ScreenUtils.getScreenSize(requireContext()).x * 0.9).toInt()
        //  ViewGroup.LayoutParams.MATCH_PARENT
        val height = (ScreenUtils.getScreenSize(requireContext()).y * 0.75).toInt()
        // ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window?.setLayout(width, height)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // WindowManager.LayoutParams
        val layoutParams = dialog.window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM

        // add animation
        layoutParams?.windowAnimations = R.style.AnimationPopupStyle

        dialog.window?.attributes = layoutParams
        dialog.setCancelable(true)

        return dialog
    }


    companion object {
        fun newInstance(date: String/* 전달할 데이터, 예: position */): CalendarDialogFragment {
            val fragment = CalendarDialogFragment()
            // 전달할 데이터를 Bundle에 담아서 설정
            val args = Bundle()
            args.putString("date", date)
            fragment.arguments = args
            return fragment
        }
    }

}