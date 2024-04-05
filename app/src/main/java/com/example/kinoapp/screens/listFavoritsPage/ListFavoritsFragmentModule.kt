package com.example.kinoapp.screens.listFavoritsPage

import androidx.lifecycle.ViewModel
import com.example.kinoapp.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListFavoritsFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListFavoritsViewModel::class)
    fun bindMedProfileViewModel(viewModel: ListFavoritsViewModel): ViewModel

}