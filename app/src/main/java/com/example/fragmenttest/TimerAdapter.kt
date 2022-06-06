package com.example.timersinwindows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.R
import kotlinx.android.synthetic.main.timer_content.view.*

class TimerAdapter(val timersList: MutableList<Timers>
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
        //var isEditVisible = false

        holder.itemView.apply {
            tvTime.text = currentTimer.getTimeString()
            tvStepTitle.text = currentTimer.getStepTitle()

            btEdit.setOnClickListener {
                tvTime.visibility = View.INVISIBLE
                tvStepTitle.visibility = View.INVISIBLE
                btDelete.visibility = View.INVISIBLE
                btSet.visibility = View.VISIBLE
                etTitleToSet.visibility = View.VISIBLE
                etTimeToSet.visibility = View.VISIBLE
                btEdit.visibility = View.INVISIBLE
            }

            btSet.setOnClickListener {
                if (etTimeToSet.text.toString().isEmpty())
                    currentTimer.setTimeInMilliSeconds(10 * 1000L)
                else
                    currentTimer.setTimeInMilliSeconds(etTimeToSet.text.toString().toLong() * 1000L)

                currentTimer.setStepTitle(etTitleToSet.text.toString())

                tvTime.text = currentTimer.getTimeString()
                tvStepTitle.text = currentTimer.getStepTitle()

                tvTime.visibility = View.VISIBLE
                tvStepTitle.visibility = View.VISIBLE
                btEdit.visibility = View.VISIBLE
                btDelete.visibility = View.VISIBLE
                etTimeToSet.visibility = View.INVISIBLE
                etTitleToSet.visibility = View.INVISIBLE
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