package com.ultimate.superheroapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ultimate.presentation.features.home.HomeState
import com.ultimate.presentation.features.home.HomeViewModel
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.databinding.FragmentHomeBinding
import com.ultimate.superheroapp.main.MainActivity
import com.ultimate.superheroapp.utils.enableNestedScroll
import com.ultimate.superheroapp.utils.onChange

class HomeFragment(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeroesRecyclerView()
        setupSquadRecyclerView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        super.onCreateView(inflater, container, savedInstanceState)?.also { binding = FragmentHomeBinding.bind(it) }

    override fun onResume() {
        super.onResume()
        viewModel.start().asLiveData().onChange(this, ::renderState)
    }

    private fun setupHeroesRecyclerView() = binding?.run {
        heroesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        heroesRecyclerView.adapter = HeroesRecyclerAdapter(::selectHero)
        heroesRecyclerView.enableNestedScroll()
    }

    private fun setupSquadRecyclerView() = binding?.run {
        squadRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        squadRecyclerView.adapter = SquadRecyclerAdapter(::selectHero)
        squadRecyclerView.enableNestedScroll()
    }

    private fun selectHero(heroId: Int) =
        (requireActivity() as MainActivity).pushDetailFragment(heroId)

    private fun renderState(state: HomeState) = when (state) {
        is HomeState.Data -> renderData(state)
        HomeState.Error,
        HomeState.Loading -> Unit
    }

    private fun renderData(state: HomeState.Data) {
        setHeroesAdapter(state.heroes)
        setSquadAdapter(state.squad)
    }

    private fun setHeroesAdapter(heroes: List<HeroItem>) = binding?.run {
        (heroesRecyclerView.adapter as HeroesRecyclerAdapter).updateItems(heroes)
    }

    private fun setSquadAdapter(squad: List<HeroItem>) = binding?.run {
        mySquadTitle.isGone = squad.isEmpty()
        (squadRecyclerView.adapter as SquadRecyclerAdapter).updateItems(squad)
    }

}
