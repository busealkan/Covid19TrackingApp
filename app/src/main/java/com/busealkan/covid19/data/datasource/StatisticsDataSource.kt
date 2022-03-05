package com.busealkan.covid19.data.datasource


import com.busealkan.covid19.Resource
import com.busealkan.covid19.data.model.StatisticsResponse
import kotlinx.coroutines.flow.Flow

interface StatisticsDataSource {
    suspend fun getStatisticsList(): Flow<Resource<List<StatisticsResponse>>>

    suspend fun getStatisticalHistory(country:String,day:String): Flow<Resource<List<StatisticsResponse>>>
}



