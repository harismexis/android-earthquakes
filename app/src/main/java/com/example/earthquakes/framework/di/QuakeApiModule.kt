package com.example.earthquakes.framework.di

import com.example.earthquakes.BuildConfig
import com.example.earthquakes.framework.data.network.api.EarthquakeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class QuakeApiModule {

    @Provides
    @Singleton
    fun provideQuakeApi(retrofit: Retrofit): EarthquakeApi {
        return retrofit.create(EarthquakeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.EARTHQUAKE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGSON(): Gson {
        return GsonBuilder().create()
    }

}