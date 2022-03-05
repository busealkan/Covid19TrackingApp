package com.busealkan.covid19.data.repository

import com.busealkan.covid19.Resource
import com.busealkan.covid19.data.model.StatisticsResponse
import kotlinx.coroutines.flow.Flow

interface StatisticsRepository {
    suspend fun getStatisticsList(): Flow<Resource<List<StatisticsResponse>>>

    suspend fun getStatisticalHistory(country:String,day:String): Flow<Resource<List<StatisticsResponse>>>

}