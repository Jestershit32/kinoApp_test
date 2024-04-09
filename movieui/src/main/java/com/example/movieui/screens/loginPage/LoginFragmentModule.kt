package com.example.movieui.screens.loginPage

import androidx.lifecycle.ViewModel
import com.example.core.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface LoginFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindMedProfileViewModel(viewModel: LoginViewModel): ViewModel
}