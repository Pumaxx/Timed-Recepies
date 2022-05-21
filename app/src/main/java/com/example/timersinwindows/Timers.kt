package com.example.timersinwindows

class Timers {
    var timeInMilliSeconds = 10000L
        private set
    private var isRunning: Boolean = false


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

    fun getIsRunning(): Boolean{
        return isRunning
    }

    fun setIsRunning(running: Boolean){
        isRunning = running
    }
}

