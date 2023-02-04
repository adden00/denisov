package com.example.testtasktinkofffintech.presentation.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testtasktinkofffintech.R
import com.example.testtasktinkofffintech.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private  lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = (childFragmentManager.findFragmentById(R.id.menuFragmentContainerView) as NavHost).navController
        binding.bottomNavigationView.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            Log.d("MyLog", "$radioGroup $i")
            when (i){
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
    }


}