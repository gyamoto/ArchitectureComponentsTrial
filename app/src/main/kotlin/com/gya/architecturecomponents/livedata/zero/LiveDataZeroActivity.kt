package com.gya.architecturecomponents.livedata.zero

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

class LiveDataZeroActivity : AppCompatActivity() {

    companion object {

        const val TAG = "LiveDataZeroActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataZeroActivity::class.java))
        }
    }

    private val observer = object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
            Log.i(TAG, "source: $source")
            Log.i(TAG, "event: $event")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_zero)

        lifecycle.addObserver(observer)
    }
}
