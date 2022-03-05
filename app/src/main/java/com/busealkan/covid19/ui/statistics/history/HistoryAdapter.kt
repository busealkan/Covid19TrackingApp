package com.busealkan.covid19.ui.statistics.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busealkan.covid19.HistoryItemClickListener
import com.busealkan.covid19.StatisticsItemClickListener
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.databinding.CardviewHistoryBinding
import com.busealkan.covid19.util.formatDateTimeCovid

class HistoryAdapter (
    var history: List<StatisticsResponse>,
    var onItemClickListener: StatisticsItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: CardviewHistoryBinding) : RecyclerView.ViewHolder(binding.root)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = CardviewHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return history.size
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder){
                binding.apply {
                    txtCountryName.text = history[position].country
                    txtCaseNumber.text = history[position].cases?.new
                    txtTestNumber.text = history[position].tests?.mPop
                    txtDeathNumber.text = history[position].deaths?.new
                    txtDate.text = history[position].time?.formatDateTimeCovid()

                    cardHistory.setOnClickListener{
                        onItemClickListener.onItemClick(history.get(position))
                    }
                }
            }
        }
}