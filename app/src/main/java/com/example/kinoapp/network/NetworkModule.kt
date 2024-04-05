package com.example.kinoapp.network

import android.content.SharedPreferences
import com.drus.githubsearch.networking.NetworkService
import com.example.kinoapp.network.repository.MovieRepository
import com.example.kinoapp.network.repository.MovieRepositoryImpl
import com.example.kinoapp.utils.StringProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        service: NetworkService,
        sharedPreferences: SharedPreferences,
        stringProvider:StringProvider
    ): MovieRepository {
        return MovieRepositoryImpl(
            networkService = service,
            sharedPreferences = sharedPreferences,
            stringProvider=stringProvider
        )
    }
}