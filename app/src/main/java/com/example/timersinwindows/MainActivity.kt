package com.example.timersinwindows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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

        val currentStep = 0
        var timerIsFinished = true
        var timerIsRunning = false
        lateinit var countdownTimer: CountDownTimer

        var currentTimeValue= 0L
        var currentTitleValue = ""

        btAddNewTimer.setOnClickListener {
            val timer = Timers()
            timerAdapter.addTimer(timer)
        }

        btStartProcess.setOnClickListener {
            if(timerIsFinished) {
                if(timerAdapter.timersList.isNotEmpty()){
                    val currentTimer = timerAdapter.timersList[currentStep]

                    currentTimeValue = currentTimer.getTimeInMilliSeconds()
                    currentTitleValue = currentTimer.getStepTitle()

                    tvMainTime.text = getTimeString(currentTimeValue)
                    tvMainStepTitle.text = currentTitleValue

                    timerAdapter.timersList.remove(currentTimer)
                    timerAdapter.notifyDataSetChanged()
                 }
            }
            if(currentTimeValue > 0L){

                timerIsRunning = true
                timerIsFinished = false

                btPause.visibility = View.VISIBLE
                btSkip.visibility = View.VISIBLE
                btStartProcess.visibility = View.INVISIBLE
                btEdit.visibility = View.INVISIBLE

                countdownTimer = object : CountDownTimer(currentTimeValue, 100){
                    override fun onFinish() {
                        tvMainStepTitle.text = ""
                        tvMainTime.text = "Done"
                        timerIsFinished = true

                        if(timerAdapter.timersList.isNotEmpty())
                            btStartProcess.callOnClick()
                    }

                    override fun onTick(p0: Long) {
                        currentTimeValue = p0
                        tvMainTime.text = getTimeString(currentTimeValue)
                    }
                }
                countdownTimer.start()
            }
        }

        btPause.setOnClickListener {
            timerIsRunning=false
            countdownTimer.cancel()


            btPause.visibility = View.INVISIBLE
            btSkip.visibility = View.INVISIBLE
            btStartProcess.visibility = View.VISIBLE
            btEdit.visibility = View.VISIBLE
        }

        btEdit.setOnClickListener {
            btStartProcess.visibility = View.INVISIBLE
            btEdit.visibility = View.INVISIBLE
            btAddNewTimer.visibility = View.VISIBLE
            btDone.visibility= View.VISIBLE
        }

        btDone.setOnClickListener {
            btAddNewTimer.visibility = View.INVISIBLE
            btDone.visibility= View.INVISIBLE
            btStartProcess.visibility = View.VISIBLE
            btEdit.visibility = View.VISIBLE
        }

        btSkip.setOnClickListener {
            if(timerAdapter.timersList.isNotEmpty()){
                countdownTimer.cancel()
                timerIsFinished = true
                btStartProcess.callOnClick()
            }
            else if (timerIsRunning){
                timerIsRunning=false
                timerIsFinished = true
                countdownTimer.cancel()

                tvMainStepTitle.text = ""
                tvMainTime.text = "Done"

                btPause.visibility = View.INVISIBLE
                btSkip.visibility = View.INVISIBLE
                btStartProcess.visibility = View.VISIBLE
                btEdit.visibility = View.VISIBLE

                currentTimeValue= 0L
            }
        }
    }
}

fun getTimeString(currentTimeValue: Long): String {
    val minutes = (currentTimeValue / 1000) / 60
    val seconds = (currentTimeValue / 1000) % 60

    if (seconds < 10) {
    }
    return "$minutes:0$seconds"
    return "$minutes:$seconds"
}