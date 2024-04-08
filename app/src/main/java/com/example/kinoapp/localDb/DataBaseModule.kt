package com.example.kinoapp.localDb

import android.content.Context
import androidx.room.Room
import com.example.kinoapp.localDb.repository.AppDatabaseRepository
import com.example.kinoapp.localDb.repository.AppDatabaseRepositoryImpl
import com.example.kinoapp.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "movieDB")
            .allowMainThreadQueries()
            .build()


    @Provides
    @Singleton
    fun providesToDoDao(database: AppDatabase) = database.getDao()

    @Provides
    @Singleton
    fun provideRepositoryRoom(service: AppDatabase): AppDatabaseRepository {
        return AppDatabaseRepositoryImpl(service)
    }

}