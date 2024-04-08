package com.example.kinoapp.screens.detailMoviePage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.kinoapp.R
import com.example.kinoapp.databinding.FragmentMoviePageBinding
import com.example.kinoapp.utils.Constants
import com.example.kinoapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class DetailMovieFragment : DaggerFragment(R.layout.fragment_movie_page) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailMovieViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentMoviePageBinding::bind)

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

        viewModel.isNetwork.observe(viewLifecycleOwner) { isTrue ->
            if (!isTrue) {
                binding.modal.visibility=View.VISIBLE
            }
        }

        binding.buttonBack.setOnClickListener { viewModel.backInMovieList() }
        binding.buttonBackModul.setOnClickListener{ viewModel.backInMovieList() }

        viewModel.movieInfoLiveData.observe(viewLifecycleOwner) { item ->
            with(binding) {

                buttonAddFavorit.setOnClickListener {
                    viewModel.addOrDeletInFavorit(movieItem = item)
                }

                tvTitle.text = item.title
                tvMovieDescription.text = item.overview.ifEmpty { getString(R.string.not_descripting) }
                tvRating.text = item.popularity.toString()
                tvRuntime.text = "${item.runtime} ${getString(R.string.minutes)}"
                tvGenres.text = item.genres.joinToString(", ")

                if (item.tagline.isEmpty()) tvTagsLabel.visibility = View.INVISIBLE
                tvTags.text = item.tagline

                ivPoster.load(Constants.HEAD_IMG_URL+item.posterPath) {
                    crossfade(true)
                }
            }
        }
    }

    companion object {

        const val MOVIE_ID_ARG = "movieIdArg"
        fun newInstance(
            idMovie: Int
        ) = DetailMovieFragment().apply {
            arguments = bundleOf(
                MOVIE_ID_ARG to idMovie
            )
        }
    }
}