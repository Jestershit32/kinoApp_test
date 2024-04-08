package com.example.kinoapp.screens.loginPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.R
import com.example.kinoapp.Screens
import com.example.kinoapp.network.repository.MovieRepository
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.StringProvider
import com.example.kinoapp.utils.exception.AuthException
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val router: Router,
    private val networkRepository: MovieRepository,
    private val stringProvider: StringProvider
) : BaseViewModel() {
    private val _errorMsg by lazy {
        MutableLiveData<String>()
    }
    val errorMsg: LiveData<String>
        get() = _errorMsg


    fun authentication(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                networkRepository.authentication(login, password)
                withContext(Dispatchers.Main) {
                    _errorMsg.value = ""
                    router.newRootScreen(Screens.movieListPage())
                }
            } catch (exception: Exception) {
                if (exception is AuthException) {
                    withContext(Dispatchers.Main) {
                        _errorMsg.value = exception.message
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _errorMsg.value = stringProvider.getString(R.string.exception_network)
                    }
                }

            }
        }
    }
}