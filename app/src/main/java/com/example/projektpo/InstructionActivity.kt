package com.example.projektpo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val button = findViewById<Button>(R.id.finnishButton)
        //id to intentValue
        val intentValue = intent.getStringExtra("Data")
        findViewById<TextView>(R.id.passed).apply {
            text = intentValue.toString()

        }
        button.setOnClickListener {

            val intent = Intent(this, FinishedInstruction::class.java).apply {
            putExtra("Data1", intentValue)
        }
            println ("!!!!!cooos baaaaardzoooooooooooooooo wazniego!!!!!" )
            startActivity(intent) }
    }
}