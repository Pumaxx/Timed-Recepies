package com.example.timersinwindows

import android.os.CountDownTimer

class Timers {
    lateinit var countdown_timer: CountDownTimer
    var timeInMilliSeconds = 0L
        private set
    var isRunning: Boolean = false

    fun pauseTimer() {
        if (isRunning)
            countdown_timer.cancel()
    }

    fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                isRunning = false
            }

            override fun onTick(p0: Long) {
                timeInMilliSeconds = p0
            }
        }
        isRunning = true
        countdown_timer.start()
    }

    fun getTimeString(): String {
        val minutes = (timeInMilliSeconds / 1000) / 60
        val seconds = (timeInMilliSeconds / 1000) % 60

        if (seconds < 10)
            return "$minutes:0$seconds"
        return "$minutes:$seconds"
    }

    fun setupTimer(startTime: Long) {
        timeInMilliSeconds = startTime
    }
}

