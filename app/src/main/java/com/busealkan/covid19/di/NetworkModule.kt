package com.busealkan.covid19.di


import com.busealkan.covid19.Constants
import com.busealkan.covid19.data.datasource.remote.Covid19Service
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.Interceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideChainRequest(chain: okhttp3.Interceptor.Chain): Request {
        return chain.request().newBuilder()
            .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "3681373598msh5f788cc032a8d32p1b4f31jsn3a6572b39100")
            .build()

    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                chain.proceed(provideChainRequest(chain))
            }
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
    }

    @Singleton
    @Provides
    fun provideMyService(retrofit: Retrofit.Builder): Covid19Service {
        return retrofit
            .build()
            .create(Covid19Service::class.java)
    }


}