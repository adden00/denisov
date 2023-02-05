package com.example.testtasktinkofffintech.presentation.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtasktinkofffintech.R
import com.example.testtasktinkofffintech.databinding.FragmentPopularBinding
import kotlinx.coroutines.flow.collect

class PopularFragment: Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: PopularFilmsAdapter

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
    }

    private fun observeListItems() {
        lifecycleScope.launchWhenStarted {
            viewModel.popularFilmsList.collect() {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isLoadingFilms.collect(){
                binding.isLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        adapter = PopularFilmsAdapter(object : PopularFilmsAdapter.Listener {
            override fun onClick() {
                requireActivity().findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_detailsFragment)

            }

        })
        binding.filmsListRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.filmsListRcView.adapter = adapter
    }
}