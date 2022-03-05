package com.busealkan.covid19

import com.busealkan.covid19.data.model.StatisticsResponse

interface StatisticsItemClickListener {
    fun onItemClick(statisticsItem: StatisticsResponse)
}

interface HistoryItemClickListener {
    fun onItemClick(position: Int)
}