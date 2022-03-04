package com.ultimate.superheroapp.herodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ultimate.presentation.features.detail.DetailViewModel
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.databinding.FragmentDetailBinding
import com.ultimate.superheroapp.utils.onChange

class HeroDetailFragment(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        super.onCreateView(inflater, container, savedInstanceState)
            ?.also { binding = FragmentDetailBinding.bind(it) }

    override fun onResume() {
        super.onResume()
        viewModel.start().asLiveData().onChange(this, ::renderState)
    }

    private fun renderState(state: Unit) = Unit
//    when (state) {
//        is HomeState.Data -> renderData(state)
//        HomeState.Error,
//        HomeState.Loading -> Unit
//    }
}