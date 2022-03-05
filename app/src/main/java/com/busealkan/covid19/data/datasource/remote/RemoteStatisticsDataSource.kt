package com.busealkan.covid19

import android.util.Log
import com.busealkan.covid19.data.datasource.StatisticsDataSource
import com.busealkan.covid19.data.datasource.remote.Covid19Service
import com.busealkan.covid19.data.model.StatisticsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RemoteStatisticsDataSource @Inject constructor(var service: Covid19Service)  : StatisticsDataSource {

    override suspend fun getStatisticsList(): Flow<Resource<List<StatisticsResponse>>> = flow {
        try {

            emit(Resource.Loading())
            val response = service.getStatisticsList()
            if (response.isSuccessful) {

                emit(Resource.Success(response.body()?.response))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
            e.printStackTrace()
        }
    }

    override suspend fun getStatisticalHistory(country: String, day: String): Flow<Resource<List<StatisticsResponse>>> = flow {
        try {

            emit(Resource.Loading())
            val response = service.getStatisticalHistory(country,day)
            if (response.isSuccessful) {

                emit(Resource.Success(response.body()?.response))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
            e.printStackTrace()
        }
    }


}
