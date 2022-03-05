package com.busealkan.covid19.data.datasource.remote

import com.busealkan.covid19.Constants

import com.busealkan.covid19.data.model.StatisticsModel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface Covid19Service {

    @GET("statistics")
    suspend fun getStatisticsList(): Response<StatisticsModel>


    @GET("history")
    suspend fun getStatisticalHistory(@Query("country") country:String,
                                        @Query("day") day:String): Response<StatisticsModel>


}

