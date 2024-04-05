package com.example.kinoapp.mainActivity

import android.content.SharedPreferences
import com.example.kinoapp.Screens
import com.example.kinoapp.presentation.BaseViewModel
import com.example.kinoapp.utils.Constants
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router,
    private val holder: NavigatorHolder,
    private val sharedPreferences: SharedPreferences

) : BaseViewModel() {

    private var isFirstAttach = true

    fun setNavigator(navigator: Navigator) {

        holder.removeNavigator()
        holder.setNavigator(navigator)
        val requiestToken = sharedPreferences.getString(Constants.REQUEST_TOKEN, null)
        val username = sharedPreferences.getString(Constants.USERNAME, null)

        if (isFirstAttach) {
            if (requiestToken != null && username != null) {
                router.newRootChain(Screens.movieListPage())
            } else {
                router.newRootChain(Screens.loginPage())
            }
            isFirstAttach = false
        }
    }


}
