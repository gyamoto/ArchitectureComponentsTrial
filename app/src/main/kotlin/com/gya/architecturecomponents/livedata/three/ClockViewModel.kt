package com.gya.architecturecomponents.livedata.three

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.gya.architecturecomponents.livedata.four.ClockMutableLiveData
import java.util.*

class ClockViewModel(context: Application) : AndroidViewModel(context) {

    val clock: LiveData<Date> by lazy { ClockMutableLiveData(getApplication()) }
}
