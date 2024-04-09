package com.example.movieui.screens.detailFavoritePage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.core.utils.Constants
import com.example.core.utils.ViewModelFactory
import com.example.movieui.R
import com.example.movieui.databinding.FragmentMovieFavoritePageBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class DetailFavoriteFragment : DaggerFragment(R.layout.fragment_movie_favorite_page) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailFavoriteViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentMovieFavoritePageBinding::bind)

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getMovieDetail(requireArguments().getInt(MOVIE_ID_ARG))

        viewModel.isFavorite.observe(viewLifecycleOwner) { isTrue ->
            if (isTrue) {
                binding.buttonAddFavorit.text = getString(R.string.delete_from_favorites)
            } else {
                binding.buttonAddFavorit.text = getString(R.string.add_in_favorites)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isTrue ->
            if (!isTrue) {
                binding.loading.visibility = View.GONE
            }
        }
        viewModel.movieInfoLiveData.observe(viewLifecycleOwner) { item ->
            with(binding) {
                buttonBack.setOnClickListener { viewModel.backToFavoritesPage() }
                buttonAddFavorit.setOnClickListener {
                    viewModel.addOrDeleteInFavorite(movieItem = item)
                }
                tvTitle.text = item.title
                tvMovieDescription.text = item.overview.ifEmpty { getString(R.string.not_descripting) }
                tvRating.text = item.rating.toString()
                tvRuntime.text = "${item.runtime} ${getString(R.string.minutes)}"
                tvGenres.text = item.genres.joinToString(", ")
                tvTags.text = item.tags
                if (item.tags.isEmpty()) tvTagsLabel.visibility = View.INVISIBLE
                ivPoster.load(Constants.HEAD_IMG_URL + item.posterPath) {
                    crossfade(true)
                }
            }
        }
    }

    companion object {

        const val MOVIE_ID_ARG = "movieIdArg"
        fun newInstance(
            idMovie: Int
        ) = DetailFavoriteFragment().apply {
            arguments = bundleOf(
                MOVIE_ID_ARG to idMovie
            )
        }
    }
}