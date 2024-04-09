package com.example.movieui.screens.loginPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core.utils.StringProvider
import com.example.core.utils.exception.AuthException
import com.example.networking.repository.MovieRepository
import com.example.movieui.R
import com.example.movieui.Screens
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