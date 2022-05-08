package com.example.projektpo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val jsonString = """
            {
                "buttons": [
                    "first button",
                    "second button",
                    "third button"
                ]
            }
        """.trimIndent()
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("buttons")

        val textYamlTable = mutableListOf<String>()
        for (i in 0 until jArray.length()) {
            textYamlTable += jArray.getString(i)
        }

        /////////////////////creating buttons///////////////////////////
        setContentView(R.layout.activity_main)
        title = "Projekt"
        val scrollView = ScrollView(this)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        scrollView.layoutParams = layoutParams
        val linearLayout = LinearLayout(this)
        val linearParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearParams

        scrollView.addView(linearLayout)

        for ((i, value) in textYamlTable.withIndex()) {
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
            btnTag.text = (value)
            btnTag.id = (i)
            row.addView(btnTag)
            btnTag.setOnClickListener {
                val intent = Intent(this, InstructionActivity::class.java).apply {
                    putExtra("Data", value)
                }
                startActivity(intent)
            }
            linearLayout.addView(row)
        }
        setContentView(scrollView)
    }
}
