package com.example.kinoapp.screens.detailMoviePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.Screens
import com.example.kinoapp.localDb.Repository.AppDatabaseRepository
import com.example.kinoapp.localDb.entitys.FavoriteMovie
import com.example.kinoapp.network.models.SimpleMovieInfoById
import com.example.kinoapp.network.repository.MovieRepository
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.Constants
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val router: Router,
    private val movieRepository: MovieRepository,
    private val databaseRepository: AppDatabaseRepository,
    private val sharedPreferences: SharedPreferences,
) : BaseViewModel() {
    private val userName = sharedPreferences.getString(Constants.USERNAME, null) ?: "ADMIN"
    private val requestToken = sharedPreferences.getString(Constants.REQUEST_TOKEN, null) ?: "ADMIN"

    private val _movieInfoLiveData: MutableLiveData<SimpleMovieInfoById> by lazy {
        MutableLiveData<SimpleMovieInfoById>()
    }
    val movieInfoLiveData: LiveData<SimpleMovieInfoById>
        get() = _movieInfoLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading




    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFavorite: MutableLiveData<Boolean>
        get() = _isFavorite


    fun getMovieDetail(id: Int) {
        val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = userName)
        isFavorite.value = movieItemByID.isNotEmpty()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieRepository.searchMovieById(movie_id = id)
                withContext(Dispatchers.Main) {
                    _movieInfoLiveData.postValue(result)
                    _isLoading.value=false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    router.backTo(Screens.movieListPage())
                }
            }
        }
    }


    fun backInMovieList() {
        router.backTo(Screens.movieListPage())
    }

    fun addOrDeletInFavorit(movieItem: SimpleMovieInfoById) {
        if (_isFavorite.value == false) {
            var genreLine: String = ""
            movieItem.genres.forEach { genre ->
                genreLine = "$genreLine ${genre.name}"
            }
            val movie =
                FavoriteMovie(
                    idRoom = null,
                    userName = userName,
                    title = movieItem.title,
                    id = movieItem.id,
                    overview = movieItem.overview,
                    rating = movieItem.popularity,
                    genres = genreLine,
                    runtime = movieItem.runtime,
                    tags = movieItem.tagline,
                    poster_path = movieItem.poster_path
                )
            databaseRepository.addInFavorite(movie)
            _isFavorite.value = true
        } else {
            _isFavorite.value = false
            databaseRepository.removeFavorite(id = movieItem.id, userName = userName)
        }


    }

}