package com.example.kinoapp.screens.detailFavoritePage

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kinoapp.Screens
import com.example.kinoapp.localDb.Repository.AppDatabaseRepository
import com.example.kinoapp.localDb.entitys.FavoriteMovie
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.Constants
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailFavoriteViewModel @Inject constructor(
    private val router: Router,
    private val databaseRepository: AppDatabaseRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _movieInfoLiveData: MutableLiveData<FavoriteMovie> by lazy {
        MutableLiveData<FavoriteMovie>()
    }
    val movieInfoLiveData: LiveData<FavoriteMovie>
        get()=_movieInfoLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isFavorite: MutableLiveData<Boolean> = MutableLiveData(false)
    val username=sharedPreferences.getString(Constants.USERNAME,"").orEmpty()

    fun getMovieDetail(id: Int) {
        val movieItemByID = databaseRepository.getFavoriteById(id = id, userName = username)
        isFavorite.value = movieItemByID.isNotEmpty()
        _movieInfoLiveData.value=movieItemByID[0]
        _isLoading.value=false
    }

    fun backToFavoritesPage() {
        router.navigateTo(Screens.favoritesPage())
    }

    fun addOrDeletInFavorit(movieItem: FavoriteMovie) {

        if (isFavorite.value == false) {
            var genreLine:String=""
            movieItem.genres.forEach{genre ->
                genreLine="$genreLine $genre"
            }

            val movie =
                FavoriteMovie(
                    idRoom = null,
                    userName = username,
                    title = movieItem.title,
                    id = movieItem.id,
                    overview = movieItem.overview,
                    rating = movieItem.rating,
                    genres = genreLine,
                    runtime = movieItem.runtime,
                    tags=movieItem.tags,
                    poster_path = movieItem.poster_path
                )
            databaseRepository.addInFavorite(movie)
            isFavorite.value = true
        } else {
            isFavorite.value = false
            databaseRepository.removeFavorite(id = movieItem.id, userName = username)

        }


    }

}