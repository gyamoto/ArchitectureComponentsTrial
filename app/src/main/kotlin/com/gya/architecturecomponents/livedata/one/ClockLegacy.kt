package com.gya.architecturecomponents.livedata.one

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.util.*

class ClockLegacy(private val context: Context) {

    private var listener: ClockListener? = null

    private val tickBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            listener?.onReceive(Calendar.getInstance().time)
        }
    }

    interface ClockListener {
        fun onReceive(date: Date)
    }

    fun setClockListener(listener: ClockListener) {
        if (this.listener != null) {
            return
        }
        this.listener = listener
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        context.registerReceiver(tickBroadcastReceiver, intentFilter)
    }

    fun removeClockListener() {
        listener = null
        context.unregisterReceiver(tickBroadcastReceiver)
    }
}