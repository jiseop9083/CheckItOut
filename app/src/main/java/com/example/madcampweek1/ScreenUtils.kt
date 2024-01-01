package com.example.madcampweek1

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.Display
import android.view.WindowManager


object ScreenUtils {

    @Suppress("DEPRECATION")
    fun getScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val screenSize = Point()

        val display = windowManager.defaultDisplay

        display?.getRealSize(screenSize)

        return screenSize

    } // x: width, y: height
}
