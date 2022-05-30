package com.example.fragmenttest.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenttest.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.CountDownTimer
import com.example.timersinwindows.TimerAdapter
import com.example.timersinwindows.Timers
import kotlinx.android.synthetic.main.recipe_layout.*
import kotlinx.android.synthetic.main.recipe_layout.view.*
import kotlinx.android.synthetic.main.timer_content.view.*
import kotlinx.android.synthetic.main.timer_content.view.btEdit

class recipeFragment : Fragment() {

    private lateinit var timerAdapter: TimerAdapter
    //private lateinit var mRecipeViewModel : recipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        timerAdapter = TimerAdapter(mutableListOf())
        view.rvTimersContainer.adapter = timerAdapter
        view.rvTimersContainer.layoutManager = LinearLayoutManager(view.context)
        
        // mRecipeViewModel = ViewModelProvider(this).get(recipeViewModel::class.java)

        val currentStep = 0
        var timerIsFinished = true
        var timerIsRunning = false
        lateinit var countdownTimer: CountDownTimer

        var currentTimeValue= 0L
        var currentTitleValue = ""

        view.btAddNewTimer.setOnClickListener {
            val timer = Timers()
            timerAdapter.addTimer(timer)
        }

        view.btStartProcess.setOnClickListener {
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

        view.btPause.setOnClickListener {
            timerIsRunning=false
            countdownTimer.cancel()

            btPause.visibility = View.INVISIBLE
            btSkip.visibility = View.INVISIBLE
            btStartProcess.visibility = View.VISIBLE
            btEdit.visibility = View.VISIBLE
        }


        view.btEdit.setOnClickListener {
            btStartProcess.visibility = View.INVISIBLE
            btEdit.visibility = View.INVISIBLE
            btAddNewTimer.visibility = View.VISIBLE
            btDone.visibility= View.VISIBLE
        }

        view.btDone.setOnClickListener {
            // insertDataToDatabase()

            btAddNewTimer.visibility = View.INVISIBLE
            btDone.visibility= View.INVISIBLE
            btStartProcess.visibility = View.VISIBLE
            btEdit.visibility = View.VISIBLE
        }

        view.btSkip.setOnClickListener {
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
        return view
    }
    /*  private fun insertDataToDatabase() {
      val dbCurrentStep = 0
      val dbStepTime: Int
      val dbStepTitle: String

      val currentTimer = timerAdapter.timersList[dbCurrentStep]
      dbStepTime = currentTimer.getTimeInMilliSeconds().toInt() / 1000
      dbStepTitle = currentTimer.getStepTitle()

      val recipeStep = recipe(0,dbStepTime,dbStepTitle)
      mRecipeViewModel.addStep(recipeStep)
  } */
}

fun getTimeString(currentTimeValue: Long): String {
    val minutes = (currentTimeValue / 1000) / 60
    val seconds = (currentTimeValue / 1000) % 60

    if (seconds < 10) {
    }
    return "$minutes:0$seconds"
    return "$minutes:$seconds"
}