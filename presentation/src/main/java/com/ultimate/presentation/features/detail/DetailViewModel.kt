package com.ultimate.presentation.features.detail

import androidx.lifecycle.ViewModel
import com.ultimate.domain.models.Hero
import com.ultimate.domain.usecases.FetchHeroUseCase
import com.ultimate.presentation.models.HeroItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

sealed class DetailState {
    object Loading : DetailState()
    data class Data(val hero: HeroItem) : DetailState()
    object Error : DetailState()
}

class DetailViewModel(
    private val fetchHeroUseCase: FetchHeroUseCase
) : ViewModel() {

    fun start(id: Int): Flow<DetailState> = flow {
        val hero = fetchHeroUseCase.run(id).getOrThrow()
        val isRecruited = false
        emit(createData(hero, isRecruited))
    }
        .onStart { DetailState.Loading }
        .onEmpty { DetailState.Error }

    fun fire() {

    }

    fun recruit() {

    }

    private fun createData(hero: Hero, isRecruited: Boolean) =
        DetailState.Data(HeroItem(hero.id, hero.name, hero.imageUrl, hero.description, isRecruited))
}