package com.gya.architecturecomponents.livedata.three

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gyamoto.myapplication.R
import kotlinx.android.synthetic.main.activity_live_data_one_two_three.*
import java.util.*

class LiveDataThreeActivity : AppCompatActivity() {

    companion object {

        const val TAG = "LiveDataThreeActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataThreeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_one_two_three)

        val viewModel = ViewModelProviders.of(this).get(ClockViewModel::class.java)
        viewModel.clock.observe(this, android.arch.lifecycle.Observer {
            it?.let { updateTime(it) }
        })
    }

    private fun updateTime(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hhmm = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        time.text = hhmm
        Log.d(TAG, hhmm)
    }
}
