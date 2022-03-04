package com.ultimate.superheroapp.herodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ultimate.presentation.features.detail.DetailState
import com.ultimate.presentation.features.detail.DetailViewModel
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.databinding.FragmentDetailBinding
import com.ultimate.superheroapp.main.MainActivity
import com.ultimate.superheroapp.utils.imageloader.GlideImageLoader
import com.ultimate.superheroapp.utils.onChange

class HeroDetailFragment(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    private var binding: FragmentDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
    }

    private fun setupBackButton() = binding?.run{
        backButton.setOnClickListener { (requireActivity() as MainActivity).pop() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        super.onCreateView(inflater, container, savedInstanceState)
            ?.also { binding = FragmentDetailBinding.bind(it) }

    override fun onResume() {
        super.onResume()
        viewModel.start(getIdArguments()).asLiveData().onChange(this, ::renderState)
    }

    private fun getIdArguments(): Int = requireArguments().getInt(HERO_ID)

    private fun renderState(state: DetailState) = when (state) {
        is DetailState.Data -> renderData(state)
        DetailState.Error,
        DetailState.Loading -> Unit
    }

    private fun renderData(state: DetailState.Data) = binding?.run {
        val item = state.hero
        GlideImageLoader().process(item.imageUrl, image)
        title.text = item.name
        description.text = item.description
        bindButton(item)
    }

    private fun bindButton(item: HeroItem) = binding?.run {
        if (item.isRecruited) {
            button.text = requireContext().getText(R.string.fire_text)
            button.setOnClickListener { viewModel.fire(item.id) }
        } else {
            button.text = requireContext().getText(R.string.recruit_text)
            button.setOnClickListener { viewModel.recruit(item.id) }
        }
    }

    companion object {
        const val HERO_ID = "HERO_ID"
    }
}