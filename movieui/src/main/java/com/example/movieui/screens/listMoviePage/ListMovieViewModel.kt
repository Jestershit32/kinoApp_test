package com.example.movieui.screens.listMoviePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.core.utils.Constants
import com.example.networking.models.SimpleMovieInfoDomain
import com.example.networking.repository.MovieRepository
import com.example.movieui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ListMovieViewModel @Inject constructor(
    private val router: Router,
    private val movieRepository: MovieRepository,
    private val sharedPreferences: SharedPreferences,

    ) : BaseViewModel() {
    private val _movieListLiveData by lazy {
        MutableLiveData<List<SimpleMovieInfoDomain>>()
    }
    val movieListLiveData: LiveData<List<SimpleMovieInfoDomain>>
        get() = _movieListLiveData

    private val _isNetwork by lazy {
        MutableLiveData<Boolean>()
    }
    val isNetwork: LiveData<Boolean>
        get() = _isNetwork

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun navigateToDetailMovie(id: Int) {
        router.navigateTo(Screens.detailMoviePage(id))
    }

    fun navigateToFavorites() {
        router.navigateTo(Screens.favoritesPage())
    }

    fun startInit() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val results = movieRepository.searchMovie(page = 1)
                withContext(Dispatchers.Main) {
                    _movieListLiveData.postValue(results)
                    _isNetwork.value = true
                    _isLoading.value = false
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (_movieListLiveData.value == null) _isNetwork.value = false
                }
            }
        }
    }

    fun logout() {
        sharedPreferences.edit()
            .putString(Constants.REQUEST_TOKEN, null)
            .putString(Constants.USERNAME, null)
            .apply()
        router.newRootScreen(Screens.loginPage())
    }

}







