package com.example.storage

import android.content.Context
import androidx.room.Room
import com.example.core.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "movieDB")
            .build()


    @Provides
    @Singleton
    fun providesToDoDao(database: AppDatabase) = database.getDao()



}