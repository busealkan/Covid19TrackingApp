package com.busealkan.covid19.di


import com.busealkan.covid19.data.datasource.StatisticsDataSource
import com.busealkan.covid19.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideStatisticsRepository(statisticsDataSource: StatisticsDataSource): StatisticsRepository {
        return StatisticsRepositoryImpl(statisticsDataSource)
    }

}




