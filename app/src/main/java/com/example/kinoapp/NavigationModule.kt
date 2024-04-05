package com.example.kinoapp

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface NavigationModule {
    companion object {
        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

        @Provides
        @Singleton
        fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }
    }
}