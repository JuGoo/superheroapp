package com.ultimate.presentation.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultimate.domain.models.Hero
import com.ultimate.domain.usecases.AddHeroToSquadUseCase
import com.ultimate.domain.usecases.CheckHeroIsRecruitedUseCase
import com.ultimate.domain.usecases.FetchHeroUseCase
import com.ultimate.domain.usecases.RemoveHeroToSquadUseCase
import com.ultimate.presentation.models.HeroItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class DetailState {
    object Loading : DetailState()
    data class Data(val hero: HeroItem) : DetailState()
    object Error : DetailState()
}

class DetailViewModel(
    private val fetchHeroUseCase: FetchHeroUseCase,
    private val addHeroToSquadUseCase: AddHeroToSquadUseCase,
    private val removeHeroToSquadUseCase: RemoveHeroToSquadUseCase,
    private val checkHeroIsRecruitedUseCase: CheckHeroIsRecruitedUseCase
) : ViewModel() {

    fun start(id: Int): Flow<DetailState> = flow {
        val hero = fetchHeroUseCase.run(id).getOrThrow()
        val isRecruited = checkHeroIsRecruitedUseCase.run(id).getOrDefault(false)
        emit(createData(hero, isRecruited))
    }
        .onStart { DetailState.Loading }
        .onEmpty { DetailState.Error }

    fun fire(id: Int) = viewModelScope.launch {
        removeHeroToSquadUseCase.run(id)
    }

    fun recruit(id: Int) = viewModelScope.launch {
        addHeroToSquadUseCase.run(id)
    }

    private fun createData(hero: Hero, isRecruited: Boolean) =
        DetailState.Data(HeroItem(hero.id, hero.name, hero.imageUrl, hero.description, isRecruited))
}