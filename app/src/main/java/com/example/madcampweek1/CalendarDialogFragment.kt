package com.example.madcampweek1

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.util.Log



    class CalendarDialogFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_dialog, container, false)
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
        fun newInstance(/* 전달할 데이터, 예: position */): CalendarDialogFragment {
            val fragment = CalendarDialogFragment()
            // 전달할 데이터를 Bundle에 담아서 설정
            val args = Bundle()
            // args.putInt("position", position)
            fragment.arguments = args
            return fragment
        }
    }


}