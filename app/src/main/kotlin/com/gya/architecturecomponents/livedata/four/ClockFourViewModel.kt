package com.gya.architecturecomponents.livedata.four

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.util.Log
import java.util.*

class ClockFourViewModel(context: Application) : AndroidViewModel(context) {

    private val tag = "ClockFourViewModel"

    private val date: LiveData<Date> by lazy { ClockMutableLiveData(getApplication()) }

    val clock: LiveData<String> = Transformations.map(date, { parseHhMm(it) })

    /**
     *  2018/01/25 19:06 → "19:06"
     */
    private fun parseHhMm(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hhmm = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))

        Log.d(tag, hhmm)
        return hhmm
    }
}
