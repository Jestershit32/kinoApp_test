package com.example.storage

import android.content.Context
import androidx.room.Room
import com.example.core.utils.ApplicationContext
import com.example.storage.repository.AppDatabaseRepository
import com.example.storage.repository.AppDatabaseRepositoryImpl
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