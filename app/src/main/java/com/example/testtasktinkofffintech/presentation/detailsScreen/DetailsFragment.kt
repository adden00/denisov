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


class DetailsFragment: Fragment() {
    private lateinit var binding: FragmentDetailsBinding
//    private lateinit var filmItem: FilmInfoItem
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
        val filmId = arguments?.getInt(FILM_ID_KEY)
        filmId?.apply {
            viewModel.loadFilmInfo(this)
        }


//        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
//        (requireActivity() as MainActivity).supportActionBar?.title = ""
        // TODO разобраться со стрелкой


        observeFilmItem()
    }

    private fun observeFilmItem() {
        lifecycleScope.launchWhenStarted {
            viewModel.filmInfo.collect(){ item ->
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



//
//
//
//filmItem = Gson().fromJson(arguments?.getString("filmItem"), FilmItem::class.java)
//binding.includedDetailsInfo.tvName.text = "${filmItem.nameRu} (${filmItem.nameEn?.apply { this }?:""})"
//binding.includedDetailsInfo.tvDescription.text = "now\nit\ngergergre\nergergr\ngsdgfdsagd\ntwhsth\naqewrgaewsg\nearfhgfdasg\nqearyer\nwertyhewryheryhgwr\n\naerhygeragdsgsag\naersdgyh"
//var str = ""
//filmItem.countries.forEach { str += it.country + ", " }
//binding.includedDetailsInfo.tvCountries.text = str