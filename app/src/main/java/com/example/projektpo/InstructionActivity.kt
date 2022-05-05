package com.example.projektpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class InstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        //id to intentValue
        val intentValue = intent.getStringExtra("Data")
        findViewById<TextView>(R.id.passed).apply {
            text = intentValue.toString()

        }

    }
}