package com.example.fragmenttest.fragments.recipe

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmenttest.R
import com.example.fragmenttest.preset.Preset
import com.example.timersinwindows.TimerAdapter
import com.example.timersinwindows.Timers
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.recipe_layout.tvMainStepTitle
import kotlinx.android.synthetic.main.recipe_layout.tvMainTime
import kotlinx.android.synthetic.main.recipe_layout.view.rvTimersContainer

class recipeFragment : Fragment() {

    private lateinit var timerAdapter: TimerAdapter
    private var presetList: List<Pair<String, Long>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        timerAdapter = TimerAdapter(mutableListOf())
        view.rvTimersContainer.adapter = timerAdapter
        view.rvTimersContainer.layoutManager = LinearLayoutManager(view.context)

        Log.d("id:", Preset.currentId.toString())
        presetList = Preset.getCurrentPresetList()
        Log.d("presetList:", presetList.toString())
        if (presetList != null) {
            loadPreset()
        }

        val currentStep = 0
        var timerIsFinished = true
        var timerIsRunning = false
        lateinit var countdownTimer: CountDownTimer

        var currentTimeValue = 0L
        var currentTitleValue: String

        view.btRecipeAddNewTimer.setOnClickListener {
            val timer = Timers()
            timerAdapter.addTimer(timer)
        }

        view.btRecipeStartProcess.setOnClickListener {
            if (timerIsFinished) {
                if (timerAdapter.timersList.isNotEmpty()) {
                    val currentTimer = timerAdapter.timersList[currentStep]

                    currentTimeValue = currentTimer.getTimeInMilliSeconds()
                    currentTitleValue = currentTimer.getStepTitle()

                    tvMainTime.text = getTimeString(currentTimeValue)
                    tvMainStepTitle.text = currentTitleValue

                    timerAdapter.timersList.remove(currentTimer)
                    timerAdapter.notifyDataSetChanged()
                }
            }
            if (currentTimeValue > 0L) {

                timerIsRunning = true
                timerIsFinished = false

                btRecipePause.visibility = View.VISIBLE
                btRecipeSkip.visibility = View.VISIBLE
                btRecipeStartProcess.visibility = View.INVISIBLE
                btRecipeEdit.visibility = View.INVISIBLE

                countdownTimer = object : CountDownTimer(currentTimeValue, 100) {
                    override fun onFinish() {
                        tvMainStepTitle.text = ""
                        tvMainTime.text = "Done"
                        timerIsFinished = true

                        if (timerAdapter.timersList.isNotEmpty())
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
            timerIsRunning = false
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
            btRecipeDone.visibility = View.VISIBLE
        }

        view.btRecipeDone.setOnClickListener {
            btRecipeAddNewTimer.visibility = View.INVISIBLE
            btRecipeDone.visibility = View.INVISIBLE
            btRecipeStartProcess.visibility = View.VISIBLE
            btRecipeEdit.visibility = View.VISIBLE
        }

        view.btRecipeSkip.setOnClickListener {
            if (timerAdapter.timersList.isNotEmpty()) {
                countdownTimer.cancel()
                timerIsFinished = true
                btRecipeStartProcess.callOnClick()
            } else if (timerIsRunning) {
                timerIsRunning = false
                timerIsFinished = true
                countdownTimer.cancel()

                tvMainStepTitle.text = ""
                tvMainTime.text = "Done"

                btRecipePause.visibility = View.INVISIBLE
                btRecipeSkip.visibility = View.INVISIBLE
                btRecipeStartProcess.visibility = View.VISIBLE
                btRecipeEdit.visibility = View.VISIBLE

                currentTimeValue = 0L
            }
        }
        return view
    }

    private fun loadPreset() {
        presetList?.forEach {
            val presetStep = Timers()
            presetStep.setStepTitle(it.first)
            presetStep.setTimeInMilliSeconds(it.second)

            timerAdapter.addTimer(presetStep)
        }
    }
}

fun getTimeString(currentTimeValue: Long): String {
    val minutes = (currentTimeValue / 1000) / 60
    val seconds = (currentTimeValue / 1000) % 60

    if (seconds < 10)
        return "$minutes:0$seconds"
    return "$minutes:$seconds"
}