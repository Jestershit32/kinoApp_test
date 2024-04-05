package com.example.kinoapp.screens.listMoviePage

import androidx.lifecycle.ViewModel
import com.example.kinoapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ListMovieFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListMovieViewModel::class)
    fun bindMedProfileViewModel(viewModel: ListMovieViewModel): ViewModel

}