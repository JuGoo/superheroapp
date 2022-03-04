package com.ultimate.presentation.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultimate.domain.models.Hero
import com.ultimate.domain.usecases.AddHeroToSquadUseCase
import com.ultimate.domain.usecases.CheckHeroIsRecruitedUseCase
import com.ultimate.domain.usecases.FetchHeroUseCase
import com.ultimate.domain.usecases.RemoveHeroToSquadUseCase
import com.ultimate.presentation.models.HeroItem
import kotlinx.coroutines.flow.*
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

    var state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Loading)

    fun start(id: Int) = viewModelScope.launch {
        emitData(id)
    }

    fun fire(id: Int) = viewModelScope.launch {
        removeHeroToSquadUseCase.run(id)
        emitData(id)
    }

    fun recruit(id: Int) = viewModelScope.launch {
        addHeroToSquadUseCase.run(id)
        emitData(id)
    }

    private suspend fun emitData(id: Int) {
        val heroItem = createHeroItem(id)
        state.value = createData(heroItem)
    }

    private suspend fun createHeroItem(id: Int): HeroItem {
        val hero = fetchHeroUseCase.run(id).getOrThrow()
        val isRecruited = checkHeroIsRecruitedUseCase.run(id).getOrDefault(false)
        return HeroItem(hero.id, hero.name, generateImageUrl(hero.imageUrl), hero.description, isRecruited)
    }
}

private fun createData(heroItem: HeroItem) = DetailState.Data(heroItem)

private fun generateImageUrl(imageUrl: String): String = "$imageUrl/portrait_fantastic.jpg"