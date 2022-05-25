package com.example.timersinwindows

class Timers {
    private var timeInMilliSeconds = 10000L
    private var stepTitle: String = "Step Title"

    fun getTimeInMilliSeconds(): Long {
        return timeInMilliSeconds
    }

    fun setTimeInMilliSeconds(time: Long) {
        timeInMilliSeconds = time
    }

    fun setStepTitle(title: String){
        stepTitle = title
    }

    fun getStepTitle(): String{
        return stepTitle
    }

    fun getTimeString(): String {
        val minutes = (timeInMilliSeconds / 1000) / 60
        val seconds = (timeInMilliSeconds / 1000) % 60

        if (seconds < 10)
            return "$minutes:0$seconds"
        return "$minutes:$seconds"
    }
}

