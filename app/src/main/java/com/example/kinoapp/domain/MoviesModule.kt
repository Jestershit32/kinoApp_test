package com.example.kinoapp.domain

import android.content.SharedPreferences
import com.example.core.utils.StringProvider
import com.example.networking.NetworkService
import com.example.networking.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MoviesModule() {
    @Provides
    @Singleton
    fun provideRepository(
        service: NetworkService,
        sharedPreferences: SharedPreferences,
        stringProvider: StringProvider
    ): MovieRepository {
        return MovieRepositoryImpl(
            networkService = service,
            sharedPreferences = sharedPreferences,
            stringProvider = stringProvider
        )
    }
}