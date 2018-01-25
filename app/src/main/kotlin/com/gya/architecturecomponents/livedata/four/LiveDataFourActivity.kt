package com.gya.architecturecomponents.livedata.four

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyamoto.myapplication.R
import com.gyamoto.myapplication.databinding.ActivityLiveDataFourBinding

class LiveDataFourActivity : AppCompatActivity() {

    companion object {

        const val TAG = "LiveDataFourActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataFourActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_four)

        val viewModel = ViewModelProviders.of(this).get(ClockFourViewModel::class.java)

        val binding: ActivityLiveDataFourBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data_four)
        binding.setLifecycleOwner(this)
        binding.clockModel = viewModel
    }
}
