package com.example.kinoapp.mainActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.kinoapp.R
import com.example.kinoapp.utils.ViewModelFactory
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResumeFragments() {
        super.onResumeFragments()

        viewModel.setNavigator(navigator)
    }

}

