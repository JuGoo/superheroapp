package com.ultimate.superheroapp.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ultimate.presentation.features.home.HomeViewModel
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.utils.onChange

class HomeFragment(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onResume() {
        super.onResume()
        viewModel.start().asLiveData().onChange(this, ::renderState)
    }

    private fun renderState(unit: Unit) = Unit
}
