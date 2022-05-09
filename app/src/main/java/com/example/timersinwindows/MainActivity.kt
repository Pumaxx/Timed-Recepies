package com.example.timersinwindows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

        private lateinit var timerAdapter: TimerAdapter

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

        timerAdapter = TimerAdapter(mutableListOf())
        rvTimersContainer.adapter = timerAdapter
        rvTimersContainer.layoutManager = LinearLayoutManager(this)

        btAddNewTimer.setOnClickListener {
            val
            val timer = Timers()
            timerAdapter.addTimer(timer)
        }
    }
}