package com.busealkan.covid19.data.repository


import com.busealkan.covid19.Resource
import com.busealkan.covid19.data.datasource.StatisticsDataSource
import com.busealkan.covid19.data.model.StatisticsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StatisticsRepositoryImpl @Inject constructor(private val dataSource: StatisticsDataSource):StatisticsRepository {

    override suspend fun getStatisticsList(): Flow<Resource<List<StatisticsResponse>>> {
        return dataSource.getStatisticsList()
    }

    override suspend fun getStatisticalHistory(country: String, day: String): Flow<Resource<List<StatisticsResponse>>> {
        return dataSource.getStatisticalHistory(country,day)
    }


}