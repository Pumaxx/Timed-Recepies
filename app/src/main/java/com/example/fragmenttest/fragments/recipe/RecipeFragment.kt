package com.example.fragmenttest.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenttest.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.CountDownTimer
import androidx.lifecycle.ViewModelProvider
import com.example.fragmenttest.MenuElement
import com.example.fragmenttest.MenuElementAdapter
import com.example.timersinwindows.TimerAdapter
import com.example.timersinwindows.Timers
import com.example.timersinwindows.data.recipe
import com.example.timersinwindows.data.recipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.recipe_layout.*
import kotlinx.android.synthetic.main.recipe_layout.btAddNewTimer
import kotlinx.android.synthetic.main.recipe_layout.btDone
import kotlinx.android.synthetic.main.recipe_layout.btEdit
import kotlinx.android.synthetic.main.recipe_layout.btPause
import kotlinx.android.synthetic.main.recipe_layout.btSkip
import kotlinx.android.synthetic.main.recipe_layout.btStartProcess
import kotlinx.android.synthetic.main.recipe_layout.tvMainStepTitle
import kotlinx.android.synthetic.main.recipe_layout.tvMainTime
import kotlinx.android.synthetic.main.recipe_layout.view.*
import kotlinx.android.synthetic.main.recipe_layout.view.rvTimersContainer
import kotlinx.android.synthetic.main.timer_content.*
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

        ciasto()
       // mRecipeViewModel = ViewModelProvider(this).get(recipeViewModel::class.java)

        val currentStep = 0
        var timerIsFinished = true
        var timerIsRunning = false
        lateinit var countdownTimer: CountDownTimer

        var currentTimeValue= 0L
        var currentTitleValue = ""

        view.btRecipeAddNewTimer.setOnClickListener {
            val timer = Timers()
            timerAdapter.addTimer(timer)
        }

        view.btRecipeStartProcess.setOnClickListener {
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

                btRecipePause.visibility = View.VISIBLE
                btRecipeSkip.visibility = View.VISIBLE
                btRecipeStartProcess.visibility = View.INVISIBLE
                btRecipeEdit.visibility = View.INVISIBLE

                countdownTimer = object : CountDownTimer(currentTimeValue, 100){
                    override fun onFinish() {
                        tvMainStepTitle.text = ""
                        tvMainTime.text = "Done"
                        timerIsFinished = true

                        if(timerAdapter.timersList.isNotEmpty())
                            btRecipeStartProcess.callOnClick()
                    }

                    override fun onTick(p0: Long) {
                        currentTimeValue = p0
                        tvMainTime.text = getTimeString(currentTimeValue)
                    }
                }
                countdownTimer.start()
            }
        }

        view.btRecipePause.setOnClickListener {
            timerIsRunning=false
            countdownTimer.cancel()

            btRecipePause.visibility = View.INVISIBLE
            btRecipeSkip.visibility = View.INVISIBLE
            btRecipeStartProcess.visibility = View.VISIBLE
            btRecipeEdit.visibility = View.VISIBLE
        }


        view.btRecipeEdit.setOnClickListener {
            btRecipeStartProcess.visibility = View.INVISIBLE
            btRecipeEdit.visibility = View.INVISIBLE
            btRecipeAddNewTimer.visibility = View.VISIBLE
            btRecipeDone.visibility= View.VISIBLE
        }

        view.btRecipeDone.setOnClickListener {
           // insertDataToDatabase()

            btRecipeAddNewTimer.visibility = View.INVISIBLE
            btRecipeDone.visibility= View.INVISIBLE
            btRecipeStartProcess.visibility = View.VISIBLE
            btRecipeEdit.visibility = View.VISIBLE
        }

        view.btRecipeSkip.setOnClickListener {
            if(timerAdapter.timersList.isNotEmpty()){
                countdownTimer.cancel()
                timerIsFinished = true
                btRecipeStartProcess.callOnClick()
            }
            else if (timerIsRunning){
                timerIsRunning=false
                timerIsFinished = true
                countdownTimer.cancel()

                tvMainStepTitle.text = ""
                tvMainTime.text = "Done"

                btRecipePause.visibility = View.INVISIBLE
                btRecipeSkip.visibility = View.INVISIBLE
                btRecipeStartProcess.visibility = View.VISIBLE
                btRecipeEdit.visibility = View.VISIBLE

                currentTimeValue= 0L
            }
        }
        return view
    }

   fun ciasto() {
       val preSetStepOne = Timers()
       val preSetStepTwo = Timers()
       val preSetStepThree = Timers()
       val preSetStepFour = Timers()
       val preSetStepFive = Timers()
       val preSetStepSix = Timers()
       val preSetStepSeven = Timers()

       preSetStepOne.setStepTitle("Wsyp do miski 400g mąki")
       preSetStepOne.setTimeInMilliSeconds(20000);
       preSetStepTwo.setStepTitle("Wsyp 80g cukru")
       preSetStepTwo.setTimeInMilliSeconds(15000);
       preSetStepThree.setStepTitle("Wbij 2 jaja")
       preSetStepThree.setTimeInMilliSeconds(30000);
       preSetStepFour.setStepTitle("Wlej 150ml melka")
       preSetStepFour.setTimeInMilliSeconds(15000);
       preSetStepFive.setStepTitle("Dokładnie wymieszaj")
       preSetStepFive.setTimeInMilliSeconds(40000);
       preSetStepSix.setStepTitle("Wlej do formy")
       preSetStepSix.setTimeInMilliSeconds(25000);
       preSetStepSeven.setStepTitle("Piecz w 220° góra-dół")
       preSetStepSeven.setTimeInMilliSeconds(1800000);

       timerAdapter.addTimer(preSetStepOne)
       timerAdapter.addTimer(preSetStepTwo)
       timerAdapter.addTimer(preSetStepThree)
       timerAdapter.addTimer(preSetStepFour)
       timerAdapter.addTimer(preSetStepFive)
       timerAdapter.addTimer(preSetStepSix)
       timerAdapter.addTimer(preSetStepSeven)

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

    if (seconds < 10)
        return "$minutes:0$seconds"
    return "$minutes:$seconds"
}