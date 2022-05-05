package com.example.projektpo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import java.util.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //////////////////buttonId/////////////
        var buttonId=0
        var buttonText="null"
        //////////////////////////////table with id///////////////////

        var textYamlTable=arrayOf("bieganie","gotowanie","pływanie","umieranie","bieganie","gotowanie","pływanie","umieranie","bieganie","gotowanie","pływanie","umieranie","umieranie","bieganie","gotowanie","pływanie","umieranie")

        /////////////////////creating buttons///////////////////////////
        setContentView(R.layout.activity_main)
        title = "Projekt"
        val scrollView = ScrollView(this)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        scrollView.layoutParams = layoutParams
        val linearLayout = LinearLayout(this)
        val linearParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearParams

        scrollView.addView(linearLayout)

        for (i in 0..textYamlTable.size-1) {
            val row = LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.gravity = Gravity.CENTER
                val btnTag = Button(this)
                btnTag.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                btnTag.text = (textYamlTable[i] )
                val test=textYamlTable[i]
                btnTag.id =(i)
                row.addView(btnTag)
                btnTag.setOnClickListener {
                    val intent= Intent(this, InstructionActivity::class.java).apply {
                        putExtra("Data",test)
                    }
                    startActivity(intent)}
            linearLayout.addView(row)
        }

        setContentView(scrollView)

    }

    private fun goToInstructionActivity() {

    }


}





