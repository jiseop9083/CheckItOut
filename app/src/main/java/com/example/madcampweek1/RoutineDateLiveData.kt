package com.example.madcampweek1

import androidx.lifecycle.MutableLiveData

class RoutineDateLiveData  constructor() {
    private var currentProgress: MutableLiveData<String> = MutableLiveData()

    fun getLiveProgress(): MutableLiveData<String> {
        return currentProgress
    }

    companion object {
        private val mInstance: RoutineDateLiveData =
            RoutineDateLiveData()
        @Synchronized
        fun getInstance(): RoutineDateLiveData {
            return mInstance
        }
    }
}