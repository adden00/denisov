package com.example.testtasktinkofffintech.presentation.homeScreen

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.testtasktinkofffintech.R
import com.example.testtasktinkofffintech.common.Constants
import com.example.testtasktinkofffintech.databinding.FragmentHomeBinding
import com.example.testtasktinkofffintech.presentation.MainActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMenu()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        navController =
            (childFragmentManager.findFragmentById(R.id.menuFragmentContainerView) as NavHost).navController
        binding.bottomNavigationView.radioGroup.setOnCheckedChangeListener { _, i ->

            setupRadioButtons(i)


        }
        (requireActivity() as MainActivity).supportActionBar?.hide()
        (requireActivity() as MainActivity).setSupportActionBar(requireActivity().findViewById(R.id.startedToolbar))
        (requireActivity() as MainActivity).supportActionBar?.show()

    }


    private fun setupRadioButtons(i: Int) {
        when (i) {
            R.id.radioButtonPopular -> {
                navController.navigate(R.id.action_favouriteFragment_to_popularFragment)
                binding.bottomNavigationView.radioButtonFavourite.isChecked = false
            }
            R.id.radioButtonFavourite -> {
                navController.navigate(R.id.action_popularFragment_to_favouriteFragment)
                binding.bottomNavigationView.radioButtonPopular.isChecked = false
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
                val searchView = menu.findItem(R.id.actionSearct).actionView as SearchView
                searchView.queryHint = "search..."
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            val curFragment =
                                if (binding.bottomNavigationView.radioButtonPopular.isChecked) Constants.POPULAR_FILM_FLAG else Constants.FAVOURITE_FILM_FLAG

                            viewModel.filterFilms(it, curFragment)
                        }
                        return false
                    }

                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return true
            }

        })
    }

}