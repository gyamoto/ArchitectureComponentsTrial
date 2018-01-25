package com.gya.architecturecomponents.livedata.two

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gyamoto.myapplication.R
import kotlinx.android.synthetic.main.activity_live_data_one_two_three.*
import java.util.*

class LiveDataTwoActivity : AppCompatActivity() {

    companion object {

        const val TAG = "LiveDataOneActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataTwoActivity::class.java))
        }
    }

    private val liveData = ClockLiveData(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_one_two_three)

        // liveData.observe → onStart-onStop
//        liveData.observe(this, android.arch.lifecycle.Observer {
//            it?.let { updateTime(it) }
//        })

        // liveData.observeForever → onCreate-onDestroy
        liveData.observeForever {
            it?.let { updateTime(it) }
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        // LiveData.value → error, MutableLiveData.value → success
//        liveData.value = Calendar.getInstance().time
//    }

    private fun updateTime(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hhmm = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        time.text = hhmm
        Log.d(TAG, hhmm)
    }
}
