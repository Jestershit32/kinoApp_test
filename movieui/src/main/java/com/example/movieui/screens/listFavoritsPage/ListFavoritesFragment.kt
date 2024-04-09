package com.example.movieui.screens.listFavoritsPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.ViewModelFactory
import com.example.movieui.R
import com.example.movieui.databinding.FragmentListOfFavoritesPageBinding
import com.example.movieui.screens.listFavoritsPage.adapter.FavoritesListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class ListFavoritesFragment : DaggerFragment(R.layout.fragment_list_of_favorites_page) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ListFavoritesViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentListOfFavoritesPageBinding::bind)
    private val adapter =
        FavoritesListAdapter { value -> viewModel.navigateToDetailFavoriteMovie(value) }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.startInit()
        viewModel.isLoading.observe(viewLifecycleOwner) { isTrue ->
            if (!isTrue) {
                binding.loading.visibility = View.GONE
            }
        }
        binding.movieRecycleView.layoutManager = LinearLayoutManager(this.context)
        binding.movieRecycleView.adapter = adapter
        binding.buttonBack.setOnClickListener {
            viewModel.navigateToListMovie()
        }
        viewModel.movieListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            if (list.isEmpty()) {
                binding.modal.visibility = View.VISIBLE
            } else {
                binding.modal.visibility = View.GONE
            }
        }
    }
}



