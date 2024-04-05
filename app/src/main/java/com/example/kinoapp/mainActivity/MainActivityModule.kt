package com.example.kinoapp.mainActivity

import androidx.lifecycle.ViewModel
import com.example.kinoapp.screens.detailFavoritePage.DetailFavoriteFragment
import com.example.kinoapp.screens.detailFavoritePage.DetailFavoriteFragmentModule
import com.example.kinoapp.screens.detailMoviePage.DetailMovieFragment
import com.example.kinoapp.screens.detailMoviePage.DetailMovieFragmentModule
import com.example.kinoapp.screens.listFavoritsPage.ListFavoritsFragment
import com.example.kinoapp.screens.listFavoritsPage.ListFavoritsFragmentModule
import com.example.kinoapp.screens.listMoviePage.ListMovieFragment
import com.example.kinoapp.screens.listMoviePage.ListMovieFragmentModule
import com.example.kinoapp.screens.loginPage.LoginFragment
import com.example.kinoapp.screens.loginPage.LoginFragmentModule
import com.example.kinoapp.utils.FragmentScope
import com.example.kinoapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMedProfileViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListMovieFragmentModule::class])
    fun contributeToListFragment(): ListMovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    fun contributeToLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailMovieFragmentModule::class])
    fun contributeToDetailMovieFragment(): DetailMovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListFavoritsFragmentModule::class])
    fun contributeToListFavoritsFragmen(): ListFavoritsFragment
    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailFavoriteFragmentModule::class])
    fun contributeToDetailFavoriteFragment(): DetailFavoriteFragment

}