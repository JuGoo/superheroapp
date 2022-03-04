package com.ultimate.presentation.features.home

import androidx.lifecycle.ViewModel
import com.ultimate.domain.models.Hero
import com.ultimate.domain.usecases.FetchHeroesUseCase
import com.ultimate.presentation.models.HeroItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

sealed class HomeState {
    object Loading : HomeState()
    data class Data(val heroes: List<HeroItem>) : HomeState()
    object Error : HomeState()
}

class HomeViewModel(private val fetchHeroesUseCase: FetchHeroesUseCase) : ViewModel() {

    fun start(): Flow<HomeState> = flow {
        val items = fetchHeroesUseCase.run().mapCatching { it.mapToItems() }.getOrThrow()
        emit(createData(items))
    }
        .onStart { HomeState.Loading }
        .onEmpty { HomeState.Error }

    private fun createData(items: List<HeroItem>) = HomeState.Data(items.sortedByName())
}

private fun List<HeroItem>.sortedByName(): List<HeroItem> = sortedBy { it.name }

private fun List<Hero>.mapToItems(): List<HeroItem> = map { it.mapItem() }

private fun Hero.mapItem(): HeroItem = HeroItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    isRecruited = false
)