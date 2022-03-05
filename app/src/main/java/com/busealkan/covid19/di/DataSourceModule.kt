package com.busealkan.covid19.di


import com.busealkan.covid19.RemoteStatisticsDataSource
import com.busealkan.covid19.data.datasource.StatisticsDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {


    @Binds
    @Singleton
    abstract fun bindStatisticsDataSource(
        dataSource: RemoteStatisticsDataSource
    ): StatisticsDataSource




}