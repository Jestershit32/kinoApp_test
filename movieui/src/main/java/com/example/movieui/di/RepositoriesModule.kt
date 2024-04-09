package com.example.movieui.di

import android.content.SharedPreferences
import com.example.core.utils.StringProvider
import com.example.movieui.domain.MovieRepositoryImpl
import com.example.networking.NetworkService
import com.example.networking.repository.MovieRepository
import com.example.storage.AppDatabase
import com.example.storage.repository.AppDatabaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {
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
    @Provides
    @Singleton
    fun provideRepositoryRoom(service: AppDatabase): AppDatabaseRepository {
        return com.example.movieui.domain.AppDatabaseRepositoryImpl(service)
    }
}