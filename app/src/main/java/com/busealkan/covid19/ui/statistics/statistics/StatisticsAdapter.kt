package com.busealkan.covid19.ui.statistics.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busealkan.covid19.StatisticsItemClickListener
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.databinding.CardviewStatisticsBinding

class StatisticsAdapter (
    var countries: List<StatisticsResponse>,
    var onItemClickListener: StatisticsItemClickListener
) : RecyclerView.Adapter<StatisticsAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: CardviewStatisticsBinding) : RecyclerView.ViewHolder(binding.root)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = CardviewStatisticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return countries.size
        }

        fun setData(sortByCountry:List<StatisticsResponse>) {
            countries = sortByCountry
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder){
                binding.apply {
                    txtCountryName.text = countries[position].country
                    txtCaseNumber.text = countries[position].cases?.new
                    txtTestNumber.text = countries[position].tests?.mPop
                    txtDeathNumber.text = countries[position].deaths?.new

                    cardStatistics.setOnClickListener{
                        onItemClickListener.onItemClick(countries.get(position))
                    }
                }
            }
        }
}