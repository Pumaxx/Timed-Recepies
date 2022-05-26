package com.example.projektpo

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FinishedInstruction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished_instruction)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val intentValue = intent.getStringExtra("Data1")
        findViewById<TextView>(R.id.textView3).apply {
            text = "you finished " + intentValue.toString()

        }


    }
}