package com.gya.architecturecomponents.livedata.one

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gyamoto.myapplication.R
import kotlinx.android.synthetic.main.activity_live_data_one_two_three.*
import java.util.*

class LiveDataOneActivity : AppCompatActivity() {

    companion object {

        const val TAG = "LiveDataOneActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataOneActivity::class.java))
        }
    }

    private val listener = object : ClockLegacy.ClockListener {
        override fun onReceive(date: Date) {
            val calendar = Calendar.getInstance()
            calendar.time = date
            val hhmm = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
            time.text = hhmm
            Log.d(TAG, hhmm)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_one_two_three)

        val clock = ClockLegacy(this)

        val observer = object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart(source: LifecycleOwner) {
                clock.setClockListener(listener)
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop(source: LifecycleOwner) {
                clock.removeClockListener()
            }
        }
        lifecycle.addObserver(observer)
    }
}
