package com.gya.architecturecomponents.livedata.two

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.util.*


class ClockLiveData(private val applicationContext: Context) : LiveData<Date>() {

    private val tag = "ClockLiveData"

    private val tickBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            postValue(Calendar.getInstance().time)
        }
    }

    override fun onActive() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        applicationContext.registerReceiver(tickBroadcastReceiver, intentFilter)
        Log.i(tag, "onActive")
    }

    override fun onInactive() {
        applicationContext.unregisterReceiver(tickBroadcastReceiver)
        Log.i(tag, "onInactive")
    }
}