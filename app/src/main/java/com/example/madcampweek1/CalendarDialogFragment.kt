package com.example.madcampweek1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.util.Log
import android.widget.TextView

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarDialogFragment : DialogFragment() {

//    private lateinit var attendView: RecyclerView
//    private lateinit var absentView: RecyclerView
//    private lateinit var attendAdapter: AttendanceAdapter
//    private lateinit var absentAdapter: AttendanceAdapter

    private lateinit var attendanceView: RecyclerView
    private lateinit var attendanceAdapter: AttendanceAdapter
    private lateinit var mContext : Context



    //// ddddddd
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context

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

        // For dialog title
        val formatterInput = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 출석부")
        val date = LocalDate.parse(argDate, formatterInput)

        // TextView에 데이터 설정
        val textView = view.findViewById<TextView>(R.id.clickedDate)
        textView.text = date.format(formatterOutput)




//        attendView = view.findViewById(R.id.attendRecyclerView)
//        absentView = view.findViewById(R.id.absentRecyclerView)
        attendanceView = view.findViewById(R.id.attendanceRecyclerView)
        //val layoutManager = GridLayoutManager(context, 2)


        // 데이터 초기화
//        val data1 = listOf("김민지", "강해린")
//        val data2 = listOf("다니엘", "하니", "혜인")
        val data = listOf(
            AttendanceAdapter.DialogItem(0, "강해린"),
            AttendanceAdapter.DialogItem(0, "김민지"),
            AttendanceAdapter.DialogItem(0, "신지섭"),
            AttendanceAdapter.DialogItem(1, "다니엘"),
            AttendanceAdapter.DialogItem(1, "하니"),
            AttendanceAdapter.DialogItem(1, "혜인")
        )

        // 어댑터 초기화
//        attendAdapter = AttendanceAdapter(data1)
//        absentAdapter = AttendanceAdapter(data2)
        attendanceAdapter = AttendanceAdapter(mContext, data)

        // 리사이클러뷰 설정
//        setupRecyclerView(attendView, attendAdapter)
//        setupRecyclerView(absentView, absentAdapter)
        attendanceView.adapter = attendanceAdapter
//        attendanceAdapter.recyclerView = attendanceView

        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

//        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanIndex(position: Int, spanCount: Int): Int {
//                return when (attendanceAdapter.getItemViewType(position)) {
//                    attendanceAdapter.TYPE_ATTEND -> 0
//                    attendanceAdapter.TYPE_ABSENT -> 1
//                    else -> 1
//                }
////                return super.getSpanIndex(position, spanCount)
//            }
////            override fun getSpanSize(position: Int): Int {
////                return when (attendanceAdapter.getItemViewType(position)) {
////                    attendanceAdapter.TYPE_ATTEND -> 0 // 1열
////                    attendanceAdapter.TYPE_ABSENT -> 1 // 2열
////
////                    // TODO: 사실 todo 아니고 개빡쳐서 달아놓음
////                    else -> 1 // 기본적으로 1열
////                }
////            }
//        }
        attendanceView.layoutManager = layoutManager

        // ItemTouchHelper 설정
//        val itemTouchHelper = ItemTouchHelper(MyItemTouchHelperCallback(attendAdapter, absentAdapter))
//        itemTouchHelper.attachToRecyclerView(attendView)
//        itemTouchHelper.attachToRecyclerView(absentView)
        val itemTouchHelper = ItemTouchHelper(MyItemTouchHelperCallback(attendanceAdapter))
        itemTouchHelper.attachToRecyclerView(attendanceView)



        return view
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_calendar_dialog, container, false)
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
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = (ScreenUtils.getScreenSize(requireContext()).y * 0.8).toInt()
        dialog.window?.setLayout(width, height)

        // WindowManager.LayoutParams를 사용하여 상단 마진 추가
        val layoutParams = dialog.window?.attributes
        layoutParams?.y = - (height * 0.02).toInt() // 상단 마진 값 (원하는 값으로 조절)
        dialog.window?.attributes = layoutParams

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

    class MyItemTouchHelperCallback(private val adapter: AttendanceAdapter) : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            Log.d("DialogFragment", "getMovementFlag: $recyclerView")
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(dragFlags, 0)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            // 아이템 위치 변경 처리
            Log.d("DialogFragment", "onMove: $recyclerView")
//            if (recyclerView == adapter1.recyclerView) {
//                adapter1.onItemMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
//            } else if (recyclerView == adapter2.recyclerView) {
//                adapter1.onItemMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
//            }

            return adapter.onItemMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)

//            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // 드래그 후 손을 뗄 때 호출됨
            Log.d("DialogFragment", "onSwiped: $direction")
        }
    }
}