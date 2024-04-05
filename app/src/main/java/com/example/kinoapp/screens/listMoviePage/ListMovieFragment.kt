package com.example.kinoapp.screens.listMoviePage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kinoapp.R
import com.example.kinoapp.databinding.FragmentListOfMoviePageBinding
import com.example.kinoapp.screens.listMoviePage.adapter.MovieListAdapter
import com.example.kinoapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class ListMovieFragment : DaggerFragment(R.layout.fragment_list_of_movie_page) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ListMovieViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentListOfMoviePageBinding::bind)
    private val adapter = MovieListAdapter { value -> viewModel.navigateToDetailMovie(value) }
    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonLogOut.setOnClickListener{viewModel.logout()}
        viewModel.startInit()
        binding.movieRecycleView.layoutManager = LinearLayoutManager(this.context)
        binding.movieRecycleView.adapter = adapter
        binding.buttonFavorites.setOnClickListener { viewModel.navigateToFavorites() }


        viewModel.isLoading.observe(viewLifecycleOwner){isTrue->
            if (!isTrue){
                binding.loading.visibility=View.GONE
            }
        }

        viewModel.movieListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.addMovieList(list)
        }
        viewModel.isNetwork.observe(viewLifecycleOwner) { isTrue ->
            if (!isTrue) {
                binding.modal.visibility = View.VISIBLE
            } else {
                binding.modal.visibility = View.GONE
            }
        }
    }
}
