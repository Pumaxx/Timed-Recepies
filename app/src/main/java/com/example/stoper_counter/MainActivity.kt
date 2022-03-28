package com.example.stoper_counter

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //references to numPickers

        val numPicker1: NumberPicker = findViewById(R.id.numberPicker1)
        val numPicker2: NumberPicker = findViewById(R.id.numberPicker2)
        val numPicker3: NumberPicker = findViewById(R.id.numberPicker3)
        val numPicker4: NumberPicker = findViewById(R.id.numberPicker4)
        //references to rest stuffs

        val time: TextView = findViewById(R.id.textViewCountDown)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        //necessary variables
        var timer: CountDownTimer? = null
        var minutes = 0;
        var min1 = 0;
        var min2 = 0;
        var seconds = 0;
        var sec1 = 0;
        var sec2 = 0;
        var taimingu: Long = 0;

        //setting value to numPickers
        numPicker1.minValue = 0
        numPicker1.maxValue = 6
        numPicker2.minValue = 0
        numPicker2.maxValue = 9
        numPicker3.minValue = 0
        numPicker3.maxValue = 6
        numPicker4.minValue = 0
        numPicker4.maxValue = 9
        button.text = "start"
        //may the show begin
        progressBar.visibility = View.INVISIBLE
        numPicker1.setOnValueChangedListener { numberPicker, i, i2 ->
            min1 = numPicker1.value;
        }
        numPicker2.setOnValueChangedListener { numberPicker, i, i2 ->
            min2 = numPicker2.value;
        }
        numPicker3.setOnValueChangedListener { numberPicker, i, i2 ->
            sec1 = numPicker3.value;
        }
        numPicker4.setOnValueChangedListener { numberPicker, i, i2 ->
            sec2 = numPicker4.value;
        }

        fun count() {
             timer = object : CountDownTimer(taimingu, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    progressBar.visibility = View.VISIBLE
                    taimingu = millisUntilFinished;
                    time.text = "$minutes : $seconds"
                    seconds -= 1;
                    if (seconds == 0) {
                        minutes -= 1
                        seconds = 60
                    }
                    if (minutes==1){
                        minutes=0
                        seconds=60
                    }
                }

                override fun onFinish() {
                    progressBar.visibility = View.INVISIBLE
                    time.text = "done!"
                }
            }.start()
        }
        button2.setOnClickListener {
            button.text="start"
            minutes = min1 * 10 + min2
            seconds = sec1 * 10 + sec2
            time.text="$minutes : $seconds"

            taimingu = ((minutes * 60 + seconds) * 1000).toLong()
            if(timer!=null){
                timer!!.cancel()
                progressBar.visibility = View.INVISIBLE}
        }
        button.setOnClickListener {

            if(button.text=="start") {
                button.text="stop"
                count()
            }
            else if(button.text=="stop"){
                button.text="start"

                time.text="$minutes : $seconds"
                if(timer!=null) {
                    timer!!.cancel()
                    taimingu = ((minutes * 60 + seconds) * 1000).toLong()
                }
                progressBar.visibility = View.INVISIBLE
            }

        }

        }
        }





