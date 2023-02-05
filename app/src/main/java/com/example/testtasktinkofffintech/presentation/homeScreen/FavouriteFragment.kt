package com.example.testtasktinkofffintech.presentation.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.FilmItem
import com.example.testtasktinkofffintech.R
import com.example.testtasktinkofffintech.common.Constants
import com.example.testtasktinkofffintech.databinding.FragmentFavouriteBinding
import com.example.testtasktinkofffintech.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: FilmsAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFilmAdapter()
        observeFavoriteFilms()
        setActopnBarTitle()
    }

    private fun setActopnBarTitle() {
        (requireActivity() as MainActivity).supportActionBar?.title =
            requireContext().getString(R.string.favourite_ru)
    }

    private fun initFilmAdapter() {
        adapter = FilmsAdapter(object : FilmsAdapter.Listener {
            override fun onClick(filmItem: FilmItem) {
                val bundle = bundleOf(Constants.FILM_ID_KEY to filmItem.filmId)
                requireActivity().findNavController(R.id.fragmentContainerView)
                    .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            }

            override fun onLongClick(filmItem: FilmItem) {
                viewModel.removeFavouriteFilm(filmItem.filmId)
                val messageSnackbar = Snackbar.make(
                    binding.root,
                    getString(R.string.film_deleted),
                    Snackbar.LENGTH_SHORT
                )
                messageSnackbar.setAction(getString(R.string.canceled_ru)) {
                    viewModel.insertFavouriteFilm(filmItem)
                }
                messageSnackbar.show()
            }
        })
        binding.favouriteRcView.adapter = adapter
        binding.favouriteRcView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeFavoriteFilms() {
        lifecycleScope.launchWhenStarted {
            viewModel.favouriteFilms.collect() {
                adapter.submitList(it)
            }
        }
    }
}