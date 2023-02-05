package com.example.testtasktinkofffintech.presentation.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testtasktinkofffintech.common.Constants.FILM_ID_KEY
import com.example.testtasktinkofffintech.databinding.FragmentDetailsBinding
import com.example.testtasktinkofffintech.presentation.MainActivity


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setActionBar()
        loadFilmInfo()
        observeFilmItem()

    }

    private fun setActionBar() {
        (requireActivity() as MainActivity).supportActionBar?.hide()
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as MainActivity).supportActionBar?.title = ""
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun loadFilmInfo() {
        val filmId = arguments?.getInt(FILM_ID_KEY)
        filmId?.apply {
            viewModel.loadFilmInfo(this)
        }
    }

    private fun observeFilmItem() {
        lifecycleScope.launchWhenStarted {
            viewModel.filmInfo.collect() { item ->
                item?.apply {
                    binding.includedDetailsInfo.tvName.text = this.nameRu
                    binding.includedDetailsInfo.tvDescription.text = this.description
                    var strBuilder = "Страны:  "
                    this.countries.forEach { strBuilder += it.country + ", " }
                    strBuilder = strBuilder.substring(0 until strBuilder.length - 2) + "."
                    binding.includedDetailsInfo.tvCountries.text = strBuilder
                    strBuilder = "Жанры:  "
                    this.genres.forEach { strBuilder += it.genre + ", " }
                    strBuilder = strBuilder.substring(0 until strBuilder.length - 2) + "."
                    binding.includedDetailsInfo.tvGenre.text = strBuilder
                    Glide.with(requireContext()).load(this.posterUrl).into(binding.imFilmPoster)

                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isLoadingInfo.collect() {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}
