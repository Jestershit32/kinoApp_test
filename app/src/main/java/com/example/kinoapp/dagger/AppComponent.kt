package com.example.kinoapp.dagger

import com.example.core.utils.BaseModule
import com.example.kinoapp.App
import com.example.movieui.di.RepositoriesModule
import com.example.navigation.NavigationModule
import com.example.networking.NetworkModule
import com.example.storage.DataBaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataBaseModule::class,
        ActivitiesModule::class,
        NetworkModule::class,
        NavigationModule::class,
        BaseModule::class,
        RepositoriesModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
