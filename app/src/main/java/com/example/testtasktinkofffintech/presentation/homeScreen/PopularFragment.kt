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
import com.example.testtasktinkofffintech.common.Constants.FILM_ID_KEY
import com.example.testtasktinkofffintech.databinding.FragmentPopularBinding
import com.example.testtasktinkofffintech.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar

class PopularFragment: Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        observeListItems()
        setActionBarTitle()
        setButtons()
    }

    private fun setButtons() {
        binding.btRefresh.setOnClickListener {
            viewModel.loadFilms()
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.loadFilms()
        }
    }

    private fun setActionBarTitle() {
        (requireActivity() as MainActivity).supportActionBar?.title = requireContext().getString(R.string.popular_ru)
    }

    private fun observeListItems() {
        lifecycleScope.launchWhenStarted {
            viewModel.popularFilmsList.collect() {
                adapter.submitList(it)
                if (it.isEmpty()) {
                    binding.noInternetMessage.visibility = View.VISIBLE
                }
                else
                    binding.noInternetMessage.visibility = View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isLoadingFilms.collect(){
                binding.isLoading.visibility = if (it) View.VISIBLE else View.GONE
                binding.swipeToRefresh.isRefreshing = it
            }
        }
    }

    private fun initRecyclerView() {
        adapter = FilmsAdapter(object : FilmsAdapter.Listener {
            override fun onClick(filmItem: FilmItem) {
                val bundle = bundleOf(FILM_ID_KEY to filmItem.filmId)
                requireActivity().findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

            }

            override fun onLongClick(filmItem: FilmItem) {
                viewModel.insertFavouriteFilm(filmItem)
                val messageSnackbar = Snackbar.make(binding.root, getString(R.string.film_added_ru), Snackbar.LENGTH_SHORT)
                messageSnackbar.setAction(getString(R.string.canceled_ru)) {
                    viewModel.removeFavouriteFilm(filmItem.filmId)
                }
                messageSnackbar.show()
            }
        })
        binding.filmsListRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.filmsListRcView.adapter = adapter



    }
}