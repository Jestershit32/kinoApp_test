package com.example.kinoapp.screens.detailMoviePage

import androidx.lifecycle.ViewModel
import com.example.kinoapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface DetailMovieFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    fun bindMedProfileViewModel(viewModel: DetailMovieViewModel): ViewModel

}