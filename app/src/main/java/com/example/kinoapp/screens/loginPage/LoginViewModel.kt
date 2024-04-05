package com.example.kinoapp.screens.loginPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.R
import com.example.kinoapp.Screens
import com.example.kinoapp.network.repository.MovieRepository
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.StringProvider
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
                val requestMsg = networkRepository.authentication(login, password)
                withContext(Dispatchers.Main) {
                    _errorMsg.value = ""
                    router.newRootScreen(Screens.movieListPage())
                }
            Log.i("error1",requestMsg.succes.toString())
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.i("error1",e.message.toString())
                    when (e.message) {
                        "Failed to connect to api.themoviedb.org/127.0.0.1:443"->_errorMsg.value = stringProvider.getString(R.string.exception_network)
                        "Connection reset" -> _errorMsg.value = stringProvider.getString(R.string.exception_network)
                        stringProvider.getString(R.string.exception_message_login_password) -> _errorMsg.value =
                            stringProvider.getString(R.string.exception_message_login_password)
                    }
                }
            }
        }
    }
}