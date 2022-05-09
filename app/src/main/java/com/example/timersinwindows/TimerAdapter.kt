package com.example.timersinwindows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.timer_content.view.*

class TimerAdapter (private val  timersList: MutableList<Timers>
) : RecyclerView.Adapter<TimerAdapter.TimerViewHolder>() {

    class TimerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        return TimerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.timer_content,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        val currentTimer = timersList[position]
        holder.itemView.apply {
            tvTime.text = currentTimer.currentTime
            currentTimer.timeInMiliSeconds = currentTimer.currentTime.toLong() *1000L

            btStart.setOnClickListener {
                currentTimer.startTimer(currentTimer.timeInMiliSeconds)
                tvTime.text = currentTimer.currentTime
            }

            btPause.setOnClickListener {
                currentTimer.pauseTimer()
                tvTime.text = currentTimer.currentTime
            }

            btEdit.setOnClickListener {
                btStart.visibility = View.INVISIBLE
                btPause.visibility = View.INVISIBLE
                tvTime.visibility = View.INVISIBLE
                btDelete.visibility = View.INVISIBLE
                btSet.visibility = View.VISIBLE
                etTimeToSet.visibility = View.VISIBLE

                btEdit.visibility = View.INVISIBLE
            }

            btSet.setOnClickListener {
                currentTimer.currentTime = etTimeToSet.text.toString()

                if(currentTimer.currentTime.isEmpty())
                    currentTimer.currentTime = "10"

                currentTimer.timeInMiliSeconds = currentTimer.currentTime.toLong() *1000L
                tvTime.text = currentTimer.currentTime

                btStart.visibility = View.VISIBLE
                btPause.visibility = View.VISIBLE
                tvTime.visibility = View.VISIBLE
                btEdit.visibility = View.VISIBLE
                btDelete.visibility = View.VISIBLE
                etTimeToSet.visibility = View.INVISIBLE

                btSet.visibility = View.INVISIBLE
            }

            btDelete.setOnClickListener {
                timersList.remove(currentTimer)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return timersList.size
    }

    fun addTimer(newTimer: Timers) {
        timersList.add(newTimer)
        notifyItemInserted(timersList.size - 1)
    }
}